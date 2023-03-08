package ui.windows;

import engine.controller.GameControllerInterface;
import exceptions.ReassignedControllerException;

public interface UIControllerInterface {
    void initController(GameControllerInterface gameController) throws ReassignedControllerException;
}
