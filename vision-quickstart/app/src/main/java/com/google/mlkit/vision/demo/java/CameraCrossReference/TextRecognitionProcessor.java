/***
 * basically a copy of "TextRecognitionProcessor" from the "textdetector" package
 * it's retyped here so i can learn what it is doing
 */

package com.google.mlkit.vision.demo.java.CameraCrossReference;

import android.content.Context;
import android.graphics.Point;
import androidx.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.demo.GraphicOverlay;
import com.google.mlkit.vision.demo.java.VisionProcessorBase;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.Text.Element;
import com.google.mlkit.vision.text.Text.Line;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import java.util.List;



public class TextRecognitionProcessor extends VisionProcessorBase<Text> {
    private static final String TAG = "TextRecProcessor";

    private final TextRecognizer textRecognizer;
    public TextRecognitionProcessor(Context context) {
        super(context);
        textRecognizer=TextRecognition.getClient();
    }

    @Override
    public void stop(){
        super.stop();
        textRecognizer.close();
    }

    @Override
    protected Task<Text> detectInImage(InputImage image){
        return textRecognizer.process(image);

    }

    @Override
    protected void onSuccess(@NonNull Text text, @NonNull GraphicOverlay graphicOverlay){
        Log.d(TAG,"text detected");
        logExtrasForTesting(text);
        graphicOverlay.add(new TextGraphic(graphicOverlay,text));
    }

    private static void logExtrasForTesting(Text text){
        if(text!=null){
            Log.v(MANUAL_TESTING_LOG,"detected text has :"+ text.getTextBlocks().size()+" blocks");
            for (int i = 0;i<text.getTextBlocks().size();i++){
                List<Line> lines =text.getTextBlocks().get(i).getLines();
                Log.v(
                        MANUAL_TESTING_LOG,String.format("text block has %d has %d lines",i,lines.size()));
                for(int j=0;j<lines.size();++j){
                    List<Element> elements=lines.get(j).getElements();
                    Log.v(
                            MANUAL_TESTING_LOG,
                            String.format("detected text line %d has %d elements",j,elements.size()));
                    for(int k=0;k<elements.size();++k){
                        Element element=elements.get(k);
                        Log.v(
                                MANUAL_TESTING_LOG,
                                String.format("text element %d says: %s",k,element.getText()));
                        Log.v(
                                MANUAL_TESTING_LOG,
                                String.format(
                                        "text element %d has bounding box: %s",
                                        k, element.getBoundingBox().flattenToString()));

                        for(Point point:element.getCornerPoints()){
                            Log.v(
                                    MANUAL_TESTING_LOG,
                                    String.format(
                                            "Expected corner point size is 4, get %d", element.getCornerPoints().length));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onFailure(@NonNull Exception e){
        Log.w(TAG,"text detection failed."+e);
    }
}
