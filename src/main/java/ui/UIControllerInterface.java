package ui;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerException;

public interface UIControllerInterface {
    void initController(GameInterface gameController) throws ReassignedControllerException;
}
