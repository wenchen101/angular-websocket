package hello;

import com.google.common.util.concurrent.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@Component
public class CounterHandler extends TextWebSocketHandler {

    WebSocketSession session;

    // This will send only to one client(most recently connected)
    public void counterIncrementedCallback(int counter) {
        System.out.println("Trying to send:" + counter);
        if (session != null && session.isOpen()) {
            try {
                System.out.println("Now sending:" + counter);
                session.sendMessage(new TextMessage("{\"value\": \"" + counter + "\"}"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Don't have open session to send:" + counter);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }

    private void handleJob(){
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Explosion> explosion = service.submit(new Callable<Explosion>() {
            public Explosion call() {
                return pushBigRedButton();
            }
        });
        Futures.addCallback(explosion, new FutureCallback<Explosion>() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(Explosion explosion) {
                walkAwayFrom(explosion);
            }
            public void onFailure(Throwable thrown) {
                battleArchNemesis(); // escaped the explosion!
            }
        });
    }

    private Explosion pushBigRedButton(){
        return null;
    }

    private void  walkAwayFrom(Explosion explosion){
    }

    private void  battleArchNemesis(){
    }

}
