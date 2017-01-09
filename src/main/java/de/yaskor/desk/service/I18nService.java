package de.yaskor.desk.service;

import de.yaskor.desk.entity.User;
import de.yaskor.desk.enums.Language;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class I18nService {

    @Resource private UserService userService;

    public ResourceBundle getResourceBundle() {
        User user = userService.getActiveUser();
        Locale locale;
        if (user != null) {
            locale = user.getLanguage() == Language.DE ? Locale.GERMAN : Locale.ENGLISH;
        }else{
            locale = Locale.ENGLISH;
        }
        return ResourceBundle.getBundle("i18n.language", locale);
    }

    /**
     * Build a internationalized string out of the messages for the given messageKey.
     *
     * @param key key of the messages.
     * @return a internationalized string.
     */
    public String msg(String key) {
        Objects.requireNonNull(key);
        try {
            return getResourceBundle().getString(key);
        } catch (Exception e) {
            log.warn("missing property for key: " + key);
            return "# MISSING Key: " + key + "! #";
        }
    }

    /**
     * Build a internationalized string for given enum value.
     *
     * @param enumeration to get internationalized string for.
     * @return a internationalized string.
     */
    public String msg(Enum<?> enumeration) {
        if (enumeration == null) {
            return "";
        }
        String type = WordUtils.uncapitalize(enumeration.getDeclaringClass().getSimpleName());
        return msg(type + "." + enumeration.name());
    }

    /**
     * Build a internationalized string out of the messages for the given messageKey.
     *
     * @param key keys of the messages.
     * @param args arguments that will be formated into the internationalized string.
     * @return a internationalized string.
     */
    public String msg(String key, Object... args) {
        try {
            return new MessageFormat(msg(key)).format(args);
        } catch (Exception ex) {
            log.error("", key);
            return "# MISSING Key: " + key + ", args: " + Arrays.toString(args) + "! #";
        }
    }
}

