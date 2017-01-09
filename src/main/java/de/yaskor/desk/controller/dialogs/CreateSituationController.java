package de.yaskor.desk.controller.dialogs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author saka
 */
@Slf4j
@Controller
@Scope("session")
public class CreateSituationController extends Dialog {

    public void show() {
        open("createSituation", 280, 115);
    }

}
