/**
 * Created by Austin on 2/14/2015.
 */
import android.content.Intent;
import android.app.IntentService;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class NotificationService extends IntentService {
    @Override
    protected void onHandleIntent(Intent workIntent) {
        String reminder = workIntent.getDataString()
        String toSpeak = write.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak,
                Toast.LENGTH_SHORT).show();
        ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
