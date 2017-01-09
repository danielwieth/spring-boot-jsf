package de.yaskor.desk.controller.dialogs;

import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;

/**
 *
 * @author saka
 */
public abstract class Dialog {

   protected void open(String name, int width, int height) {
      Map<String, Object> options = new HashMap<>();
      options.put("modal", true);
      options.put("width", width);
      options.put("height", height);
      options.put("draggable", false);
      options.put("resizable", false);
      options.put("contentWidth", "100%");
      options.put("contentHeight", "100%");
      options.put("headerElement", "custom-header");
      RequestContext.getCurrentInstance().openDialog("/views/dialogs/" + name, options, null);
   }

   public void close() {
      RequestContext.getCurrentInstance().closeDialog(null);
   }
}
