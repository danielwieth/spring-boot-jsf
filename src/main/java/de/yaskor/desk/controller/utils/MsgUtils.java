package de.yaskor.desk.controller.utils;

import de.yaskor.desk.service.I18nService;
import de.yaskor.desk.utils.KException;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author samil kale
 */
@Slf4j
@Scope("session")
@Controller("msgUtils")
public class MsgUtils implements Serializable {

    @Resource private I18nService i18nService;

    public String msg(String key) {
        return i18nService.msg(key);
    }

    public String msg(String key, Object... args) {
        return i18nService.msg(key, args);
    }

    public String msg(Enum<?> enumeration) {
        return i18nService.msg(enumeration);
    }

    public void showMessage(FacesMessage.Severity severity, String messageKey, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(severity, msg(messageKey, args), null);
        context.addMessage(null, message);
    }

    public void showMessage(FacesMessage.Severity severity, Throwable t) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message;
        if (t instanceof KException) {
            message = new FacesMessage(severity, msg(((KException) t).getMessageKey()), null);
        } else {
            message = new FacesMessage(severity, t.getMessage(), null);
        }
        context.addMessage(null, message);
    }
}
