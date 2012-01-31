package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.logic.LessonManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kresshy
 */
public final class UploadPanel extends Panel {

    private static final Logger log = LoggerFactory.getLogger(UploadPanel.class);

    public UploadPanel(String id) {
        super(id);
    }
    private FileUploadField fileUpload;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final List<String> labellist = new LinkedList();
        final Map<String, String> lessons = new HashMap<String, String>();

        final Form uploadForm = new Form("simpleUpload") {

            @Override
            protected void onSubmit() {
                super.onSubmit();

                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    try {

                        InputStream inputStream = uploadedFile.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream);
                        BufferedReader br = new BufferedReader(reader);

                        br.readLine(); //fejléc, nem kell

                        String strLine;
                        String[] splitline;

                        while ((strLine = br.readLine()) != null) {

                            splitline = strLine.split(";");
                            String lessonName = splitline[0];
                            String lessonCode = splitline[1];
                            labellist.add(lessonName + " " + lessonCode);
                            lessons.put(lessonCode, lessonName);
                        }

                        br.close();
                        reader.close();
                        inputStream.close();
                        
                        LessonManager.getInstance().importLessons(lessons);

                    } catch (Exception e) {
                        error("Az adatokat nem sikerült importálni");
                        log.warn("Can't import lessons.", e);
                    }

                }
            }
        };

        uploadForm.setMultiPart(true);
        uploadForm.setMaxSize(Bytes.megabytes(10));
        uploadForm.add(fileUpload = new FileUploadField("fileUpload"));
        add(uploadForm);

        Form parseForm = new Form("parseform");

        final ListView listView = new ListView("listview", labellist) {

            @Override
            protected void populateItem(ListItem li) {
                li.add(new Label("labels", li.getModel()));
            }
        };
        add(parseForm);
        parseForm.add(listView);
    }
}
