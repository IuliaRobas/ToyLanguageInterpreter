package tests;


import exceptions.ExpressionException;
import exceptions.MyStackException;
import model.*;
import model.Controller;
import model.expressions.ArithExp;
import model.expressions.ConstExp;
import model.expressions.VarExp;
import model.IRepository;
import model.Repository;
import model.statements.*;
import model.structures.*;

import java.io.IOException;


public class Tests {


    public static void testFile() {

        IStmt stmt1 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(new readFile(new VarExp("var_f"), "var_c"),

                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExp("var_f"), "var_c"),
                                                        new PrintStmt(new VarExp("var_c"))
                                                ),
                                                new PrintStmt(new ConstExp(0))
                                        ),
                                        new closeRFile(new VarExp("var_f")))
                        )
                ));

        IStmt stmt2 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile(new ArithExp('+', new VarExp("var_f"), new ConstExp(2)),"var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt(new VarExp("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExp("var_f"),"var_c"),
                                                        new PrintStmt(new VarExp("var_c"))),
                                                new PrintStmt(new ConstExp(0))),
                                        new closeRFile(new VarExp("var_f")))

                                )

                        )
                        );
    }

    public static void test1() {
        ArithExp e1 = new ArithExp('-', new ConstExp(3), new ConstExp(2));
        IStmt s = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));
        IStmt c = new CompStmt(new AssignStmt("s", new ArithExp('+', new ConstExp(55), new ConstExp(100))), new PrintStmt(new VarExp("c")));
        //System.out.println(c);

        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));
        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp('+', new ConstExp(2), new
                ArithExp('*', new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));

        //System.out.println(ex1);
        //System.out.println(ex2);

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-', new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        //System.out.println(ex3);

        IRepository repository = new Repository() {
        };
        Controller controller = new Controller(repository);

        MyIStack<IStmt> stk = new MyStack<IStmt>();
        //stk.push(ex3);
        MyIDictionary<String, Integer> symtbl = new MyDictionary<String, Integer>();
        MyIList<Integer> out = new MyList<Integer>();

        PrgState crtPrgState = new PrgState(stk, symtbl, out);
        repository.add(crtPrgState);

        /*try{
            controller.allSteps();
        }
        catch(MyStackException e)  { System.out.println(e.getMessage());}
        catch(ExpressionException ee){System.out.println(ee.getMessage());};

        */

        IStmt err = new AssignStmt("a", new ArithExp('/', new ConstExp(2), new ConstExp(0)));
        err = new AssignStmt("a", new VarExp("b"));
        repository = new Repository() {
        };
        controller = new Controller(repository);

        stk = new MyStack<IStmt>();
        stk.push(err);
        symtbl = new MyDictionary<String, Integer>();
        out = new MyList<Integer>();

        crtPrgState = new PrgState(stk, symtbl, out);
        repository.add(crtPrgState);
        try {
            controller.allSteps();
        } catch (MyStackException e) {
            System.out.println(e.getMessage());
        } catch (ExpressionException ee) {
            System.out.println(ee.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }
}
