package ui;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerExeption;

public interface UIControllerInterface {
    void initController(GameInterface gameController) throws ReassignedControllerExeption;
}
