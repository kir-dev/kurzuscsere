/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.panel;

import java.io.File;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
    
    }
    
    
}
