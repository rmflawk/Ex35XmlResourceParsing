package com.youngstudio.ex35xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);


    }


    public void clickBtn(View view) {
        //Resources폴더 안에 있는 xml문서를 읽어와서 분석(parse)하는 작업 수행

        //res폴더 관리자 객체 소환
        Resources res= getResources();

        //창고관리자로부터 파서객체(분석가) 얻어오기
        XmlResourceParser xrp= res.getXml(R.xml.movies);

        StringBuffer buffer= new StringBuffer();

        //xml파서에게 xml문서를 분석하도록 코딩

        try {
            xrp.next();
            int eventType= xrp.getEventType();

            String tagName;
            String text;

            while(eventType != XmlResourceParser.END_DOCUMENT){

                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT: //0
                        buffer.append("xml 파싱 시작합니다.\n\n");
                        break;

                    case XmlResourceParser.START_TAG: //2
                        tagName= xrp.getName(); // 태그문의 이름 얻어오기
                        if(tagName.equals("no")){
                            buffer.append("번호:");
                        }else if(tagName.equals("title")){
                            buffer.append("제목:");
                        }else if(tagName.equals("장르")){
                            buffer.append("장르:");
                        }
                        break;

                    case XmlResourceParser.TEXT: //4
                        text= xrp.getText();
                        buffer.append(text);
                        break;

                    case XmlResourceParser.END_TAG: //3
                        tagName= xrp.getName();
                        if(tagName.equals("no")){
                            buffer.append("\n");
                        }else if(tagName.equals("title")){
                            buffer.append("\n");
                        }else if(tagName.equals("장르")){
                            buffer.append("\n");
                        }else if(tagName.equals("item")){
                            buffer.append("\n\n");
                        }
                        break;

                    case XmlResourceParser.END_DOCUMENT: //1
                        break;
                }//switch
                eventType= xrp.next();
            }//while

            buffer.append("파싱 종료..");

            tv.setText(buffer.toString());


        } catch (IOException e) {e.printStackTrace();
        } catch (XmlPullParserException e) {e.printStackTrace(); }


    }//clickBtn
}//MainActivity




































