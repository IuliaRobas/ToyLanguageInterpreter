package model.commands;

import exceptions.ExpressionException;
import exceptions.MyStackException;
import model.Controller;

import java.io.IOException;

public class RunCommand extends Command {
    private Controller ctrl;
    public RunCommand(String key, String desc,Controller ctrl){
        super(key, desc);
        this.ctrl=ctrl;
    }
    @Override
    public void execute() throws ExpressionException, MyStackException,IOException {
        try {
            ctrl.allSteps();
        }
        catch (MyStackException e) {
            throw e;
        } catch (ExpressionException ee) {
            throw ee;
        } catch (IOException ioe) {
            throw ioe;
        }
    }
}
