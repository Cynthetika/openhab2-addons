package org.openhab.binding.aqualink.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebSocket(maxTextMessageSize = 64 * 1024)
public class AqualinkWebSocketClient {

    String destUri = "ws://192.168.0.225:6500";
    private Logger logger = LoggerFactory.getLogger(AqualinkWebSocketClient.class);
    private String lastMessage;
    private JsonObject lastLEDStatusMessage;

    @SuppressWarnings("unused")
    private Session session;
    private final JsonParser jsonParser;
    private final Gson gson;
    private AqualinkStatusListener myListener;

    // CountDownLatch closeLatch;
    WebSocketClient client;

    public AqualinkWebSocketClient(String destUri, AqualinkStatusListener listener) {
        this.destUri = destUri;
        // this.closeLatch = new CountDownLatch(1); // just one child thread
        this.jsonParser = new JsonParser();
        this.gson = createGsonBuilder().create();
        this.myListener = listener;
        lastMessage = "";
        lastLEDStatusMessage = new JsonObject();

    }

    public void connect() {
        try {
            this.client = new WebSocketClient();
            client.start();
            URI echoUri = new URI(destUri);
            ArrayList<String> subprotocols = new ArrayList<String>();
            subprotocols.add("dumb-increment-protocol");
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setSubProtocols(subprotocols);
            client.connect(this, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);

            // wait for closed socket connection.
            // socket.awaitClose(50,TimeUnit.SECONDS);
            // closeLatch.await(); // wait until child dies
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            if (this.client != null) {
                this.client.stop();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void sendCommand(String cmd) {
        try {
            Future<Void> fut;
            fut = session.getRemote().sendStringByFuture("{\"command\":\"" + cmd + "\"}");
            fut.get(2, TimeUnit.SECONDS); // wait for send to complete.
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void togglePoolSwitchState() {
        sendCommand("KEY_PUMP");
    }

    public void toggleSpaSwitchState() {
        sendCommand("KEY_SPA");
    }

    public void toggleAux1State() {
        sendCommand("KEY_AUX1");
    }

    public void toggleAux2State() {
        sendCommand("KEY_AUX2");
    }

    public void toggleAux3State() {
        sendCommand("KEY_AUX3");
    }

    public void toggleAux4State() {
        sendCommand("KEY_AUX4");
    }

    public void toggleAux5State() {
        sendCommand("KEY_AUX5");
    }

    public void toggleAux6State() {
        sendCommand("KEY_AUX6");
    }

    public void toggleAux7State() {
        sendCommand("KEY_AUX7");
    }

    public void togglePoolHeaterState() {
        sendCommand("KEY_POOL_HTR");
    }

    public void toggleSpaHeaterState() {
        sendCommand("KEY_SPA_HTR");
    }

    public void toggleSolarHeaterState() {
        sendCommand("KEY_HTR_SOLAR");
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
        this.session = null;
        myListener.aqualinkConnectionDropped(reason);
        // this.closeLatch.countDown(); // trigger latch
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.printf("Got connect: %s%n", session);
        this.session = session;
        myListener.aqualinkConnectionEstablished();
        try {
            Future<Void> fut;
            fut = session.getRemote().sendStringByFuture("GET_AUX_LABELS");
            fut.get(2, TimeUnit.SECONDS); // wait for send to complete.

            // session.close(StatusCode.NORMAL,"I'm done");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        if (msg.equals(lastMessage)) {
            // don't process same message
        } else {
            try {
                lastMessage = msg;
                JsonObject json = jsonParser.parse(msg).getAsJsonObject();
                if (json.get("type").getAsString().equals("status")) {
                    logger.debug("Posted status message " + json);
                    myListener.aqualinkStatusChanged(gson.fromJson(json, AqualinkStatus.class));

                    JsonObject ledsJson = json.get("leds").getAsJsonObject();
                    if (!ledsJson.equals(lastLEDStatusMessage)) {
                        lastLEDStatusMessage = ledsJson;
                        myListener.aqualinkLEDStatusChanged(gson.fromJson(ledsJson, AqualinkLEDStatus.class));
                    }

                }
            } catch (Exception e) {
                logger.error("Dropping Error with message " + msg);
            }
        }

    }

    private GsonBuilder createGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder;
    }

}
