package de.yaskor.desk.controller;

import de.yaskor.desk.controller.dialogs.CreateSituationController;
import de.yaskor.desk.entity.Situation;
import de.yaskor.desk.entity.User;
import de.yaskor.desk.enums.SituationType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
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
public class DeskController {

    @Resource private CreateSituationController createSituationController;
    @Getter @Setter private List<Situation> situations;

    @PostConstruct
    private void postConstruct() {
        Situation situation1 = new Situation();
        situation1.setAssignee(new User());
        situation1.setCustomer("hans");
        situation1.setPriority(4);
        situation1.setProject("john");
        situation1.setType(SituationType.BUG);
        Situation situation2 = new Situation();
        situation2.setAssignee(new User());
        situation2.setCustomer("tuk");
        situation2.setPriority(4);
        situation2.setProject("muk");
        situation2.setType(SituationType.BUG);
    }

    public void preRenderView() {
        situations = new ArrayList<>();
    }

    public void onCreateSituation() {
        createSituationController.show();
    }
}
