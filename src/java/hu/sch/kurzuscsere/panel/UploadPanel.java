/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.panel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Bytes;

/**
 *
 * @author Kresshy
 */
public final class UploadPanel extends Panel {

    public UploadPanel(String id) {
        super(id);
    }
    private FileUploadField fileUpload;
    private String UPLOAD_FOLDER = "C:\\";

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form uploadForm = new Form("simpleUpload") {

            @Override
            protected void onSubmit() {
                super.onSubmit();

                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    File newFile = new File(UPLOAD_FOLDER
                            + uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        info("saved file: " + uploadedFile.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }

                }
            }
        };

        uploadForm.setMultiPart(true);
        uploadForm.setMaxSize(Bytes.megabytes(10));
        uploadForm.add(fileUpload = new FileUploadField("fileUpload"));
        add(uploadForm);

        Form parseForm = new Form("parseform");

        final List<String[]> labellist = new ArrayList<String[]>();

        final ListView listView = new ListView("listview", labellist) {

            @Override
            protected void populateItem(ListItem li) {
                li.add(new Label("labels", li.getModel()));
            }
        };
        add(parseForm);
        parseForm.add(listView);

        parseForm.add(new Button("btnlist") {

            @Override
            public void onSubmit() {
                super.onSubmit();

                try {
                    FileInputStream fis = new FileInputStream("c:\\valami.csv");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    
                    String strLine;
                    String[] splitline;
                    
                    while (( strLine = br.readLine()) != null) {
                        splitline = strLine.split("\\;");
                        labellist.add(splitline);                        
                    }
                    
                    isr.close();
                    fis.close();
                    
                } catch (Exception e){
                
                }
            
            }
            
        });
    
    }
    
    
}
