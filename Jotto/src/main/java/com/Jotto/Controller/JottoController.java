package com.Jotto.Controller;

import com.Jotto.Bean.Alphabet;
import com.Jotto.Bean.GameState;
import com.Jotto.Bean.JottoAI;
import com.Jotto.Bean.LetterState;
import com.Jotto.Bean.SecretWord;
import com.Jotto.Service.GameService;
import com.Jotto.Service.GuessService;
import com.Jotto.Service.UserService;
import com.Jotto.Service.WordService;
import com.Jotto.model.Game;
import com.Jotto.model.JottoForm;
import com.Jotto.model.User;
import com.Jotto.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class JottoController {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GuessService guessService;

    private static GameState gameState;

    @RequestMapping(value={"/secretword"}, method = RequestMethod.GET)
    public ModelAndView secretWord() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("secretWord", new SecretWord(""));
        modelAndView.setViewName("secretword");
        return modelAndView;
    }

    @RequestMapping(value = "/secretword", method = RequestMethod.POST)
    public ModelAndView userSubmitsWord(@ModelAttribute SecretWord secretWord){
        ModelAndView modelAndView = new ModelAndView();
        Word wordExists = wordService.findWordByWord(secretWord.getWord());
        if (wordExists == null) {
            modelAndView.addObject("message",  secretWord.getWord() + " is not valid.");
            modelAndView.addObject("secretword", new SecretWord(""));
            modelAndView.setViewName("secretword");
            return modelAndView;
        }
        else if(!isUnique(secretWord.getWord())){
            modelAndView.addObject("message",  secretWord.getWord() + " all letters must be unique");
            modelAndView.addObject("secretword", new SecretWord(""));
            modelAndView.setViewName("secretword");
            return modelAndView;
        }
        else{
//            initialize game state
            ArrayList<String> userGuesses = new ArrayList<String>();
            ArrayList<String> compGuesses = new ArrayList<String>();
            ArrayList<Integer> userPoints = new ArrayList<Integer>();
            ArrayList<Integer> compPoints = new ArrayList<Integer>();
            Word compSecret = wordService.findRandomWord();
            gameState = new GameState(false , userGuesses, compGuesses,userPoints,compPoints, secretWord.getWord(), compSecret.getWord());
            System.out.println(secretWord.getWord());
            System.out.println(compSecret.getWord());
            modelAndView = new ModelAndView(new RedirectView("/jotto"));
            return modelAndView;
        }

    }

    private List<Word> wordList;

    @RequestMapping(value={"/jotto"}, method = RequestMethod.GET)
    public ModelAndView jotto(){
        ModelAndView modelAndView = new ModelAndView();
//        Word word = wordService.findWordByWord("place");
        JottoForm form = new JottoForm();
        modelAndView.addObject("form", form);
        modelAndView.addObject("userSecret", "This is your secret: " +gameState.getUserSecret());
        //modelAndView.addObject("userSecret", "This is your secret: " +gameState.getUserSecret());
//        modelAndView.addObject("word", word);
        modelAndView.setViewName("jotto");
        wordList = wordService.findAll();
        return modelAndView;
    }

    public static void setCompStates(JottoForm jottoForm) {
        if (gameState.getUserSecret().contains("a")) jottoForm.setaFieldComp("green"); else jottoForm.setaFieldComp("red");
        if (gameState.getUserSecret().contains("b")) jottoForm.setbFieldComp("green"); else jottoForm.setbFieldComp("red");
        if (gameState.getUserSecret().contains("c")) jottoForm.setcFieldComp("green"); else jottoForm.setcFieldComp("red");
        if (gameState.getUserSecret().contains("d")) jottoForm.setdFieldComp("green"); else jottoForm.setdFieldComp("red");
        if (gameState.getUserSecret().contains("e")) jottoForm.seteFieldComp("green"); else jottoForm.seteFieldComp("red");
        if (gameState.getUserSecret().contains("f")) jottoForm.setfFieldComp("green"); else jottoForm.setfFieldComp("red");
        if (gameState.getUserSecret().contains("g")) jottoForm.setgFieldComp("green"); else jottoForm.setgFieldComp("red");
        if (gameState.getUserSecret().contains("h")) jottoForm.sethFieldComp("green"); else jottoForm.sethFieldComp("red");
        if (gameState.getUserSecret().contains("i")) jottoForm.setiFieldComp("green"); else jottoForm.setiFieldComp("red");
        if (gameState.getUserSecret().contains("j")) jottoForm.setjFieldComp("green"); else jottoForm.setjFieldComp("red");
        if (gameState.getUserSecret().contains("k")) jottoForm.setkFieldComp("green"); else jottoForm.setkFieldComp("red");
        if (gameState.getUserSecret().contains("l")) jottoForm.setlFieldComp("green"); else jottoForm.setlFieldComp("red");
        if (gameState.getUserSecret().contains("m")) jottoForm.setmFieldComp("green"); else jottoForm.setmFieldComp("red");
        if (gameState.getUserSecret().contains("n")) jottoForm.setnFieldComp("green"); else jottoForm.setnFieldComp("red");
        if (gameState.getUserSecret().contains("o")) jottoForm.setoFieldComp("green"); else jottoForm.setoFieldComp("red");
        if (gameState.getUserSecret().contains("p")) jottoForm.setpFieldComp("green"); else jottoForm.setpFieldComp("red");
        if (gameState.getUserSecret().contains("q")) jottoForm.setqFieldComp("green"); else jottoForm.setqFieldComp("red");
        if (gameState.getUserSecret().contains("r")) jottoForm.setrFieldComp("green"); else jottoForm.setrFieldComp("red");
        if (gameState.getUserSecret().contains("s")) jottoForm.setsFieldComp("green"); else jottoForm.setsFieldComp("red");
        if (gameState.getUserSecret().contains("t")) jottoForm.settFieldComp("green"); else jottoForm.settFieldComp("red");
        if (gameState.getUserSecret().contains("u")) jottoForm.setuFieldComp("green"); else jottoForm.setuFieldComp("red");
        if (gameState.getUserSecret().contains("v")) jottoForm.setvFieldComp("green"); else jottoForm.setvFieldComp("red");
        if (gameState.getUserSecret().contains("w")) jottoForm.setwFieldComp("green"); else jottoForm.setwFieldComp("red");
        if (gameState.getUserSecret().contains("x")) jottoForm.setxFieldComp("green"); else jottoForm.setxFieldComp("red");
        if (gameState.getUserSecret().contains("y")) jottoForm.setaFieldComp("green"); else jottoForm.setyFieldComp("red");
        if (gameState.getUserSecret().contains("z")) jottoForm.setaFieldComp("green"); else jottoForm.setaFieldComp("red");
    }

    public static void formToUserState(JottoForm form, Alphabet userState) {
        switch (form.getaField()) {case "green": userState.setA(LetterState.IN_WORD);break;case "red": userState.setA(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setA(LetterState.UNKNOWN); }
        switch (form.getbField()) {case "green": userState.setB(LetterState.IN_WORD);break;case "red": userState.setB(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setB(LetterState.UNKNOWN); }
        switch (form.getcField()) {case "green": userState.setC(LetterState.IN_WORD);break;case "red": userState.setC(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setC(LetterState.UNKNOWN); }
        switch (form.getdField()) {case "green": userState.setD(LetterState.IN_WORD);break;case "red": userState.setD(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setD(LetterState.UNKNOWN); }
        switch (form.geteField()) {case "green": userState.setE(LetterState.IN_WORD);break;case "red": userState.setE(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setE(LetterState.UNKNOWN); }
        switch (form.getfField()) {case "green": userState.setF(LetterState.IN_WORD);break;case "red": userState.setF(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setF(LetterState.UNKNOWN); }
        switch (form.getgField()) {case "green": userState.setG(LetterState.IN_WORD);break;case "red": userState.setG(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setG(LetterState.UNKNOWN); }
        switch (form.gethField()) {case "green": userState.setH(LetterState.IN_WORD);break;case "red": userState.setH(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setH(LetterState.UNKNOWN); }
        switch (form.getiField()) {case "green": userState.setI(LetterState.IN_WORD);break;case "red": userState.setI(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setI(LetterState.UNKNOWN); }
        switch (form.getjField()) {case "green": userState.setJ(LetterState.IN_WORD);break;case "red": userState.setJ(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setJ(LetterState.UNKNOWN); }
        switch (form.getkField()) {case "green": userState.setK(LetterState.IN_WORD);break;case "red": userState.setK(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setK(LetterState.UNKNOWN); }
        switch (form.getlField()) {case "green": userState.setL(LetterState.IN_WORD);break;case "red": userState.setL(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setL(LetterState.UNKNOWN); }
        switch (form.getmField()) {case "green": userState.setM(LetterState.IN_WORD);break;case "red": userState.setM(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setM(LetterState.UNKNOWN); }
        switch (form.getnField()) {case "green": userState.setN(LetterState.IN_WORD);break;case "red": userState.setN(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setN(LetterState.UNKNOWN); }
        switch (form.getoField()) {case "green": userState.setO(LetterState.IN_WORD);break;case "red": userState.setO(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setO(LetterState.UNKNOWN); }
        switch (form.getpField()) {case "green": userState.setP(LetterState.IN_WORD);break;case "red": userState.setP(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setP(LetterState.UNKNOWN); }
        switch (form.getqField()) {case "green": userState.setQ(LetterState.IN_WORD);break;case "red": userState.setQ(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setQ(LetterState.UNKNOWN); }
        switch (form.getrField()) {case "green": userState.setR(LetterState.IN_WORD);break;case "red": userState.setR(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setR(LetterState.UNKNOWN); }
        switch (form.getsField()) {case "green": userState.setS(LetterState.IN_WORD);break;case "red": userState.setS(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setS(LetterState.UNKNOWN); }
        switch (form.gettField()) {case "green": userState.setT(LetterState.IN_WORD);break;case "red": userState.setT(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setT(LetterState.UNKNOWN); }
        switch (form.getuField()) {case "green": userState.setU(LetterState.IN_WORD);break;case "red": userState.setU(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setU(LetterState.UNKNOWN); }
        switch (form.getvField()) {case "green": userState.setV(LetterState.IN_WORD);break;case "red": userState.setV(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setV(LetterState.UNKNOWN); }
        switch (form.getwField()) {case "green": userState.setW(LetterState.IN_WORD);break;case "red": userState.setW(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setW(LetterState.UNKNOWN); }
        switch (form.getxField()) {case "green": userState.setX(LetterState.IN_WORD);break;case "red": userState.setX(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setX(LetterState.UNKNOWN); }
        switch (form.getyField()) {case "green": userState.setY(LetterState.IN_WORD);break;case "red": userState.setY(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setY(LetterState.UNKNOWN); }
        switch (form.getzField()) {case "green": userState.setZ(LetterState.IN_WORD);break;case "red": userState.setZ(LetterState.NOT_IN_WORD);break;case "black": case "":userState.setZ(LetterState.UNKNOWN); }
    }

    public static void userStateToForm(ModelAndView modelAndView, Alphabet userState) {
        switch (userState.getA()) {case LetterState.UNKNOWN: modelAndView.addObject("aField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("aField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("aField", "green"); }
        switch (userState.getB()) {case LetterState.UNKNOWN: modelAndView.addObject("bField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("bField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("bField", "green"); }
        switch (userState.getC()) {case LetterState.UNKNOWN: modelAndView.addObject("cField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("cField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("cField", "green"); }
        switch (userState.getD()) {case LetterState.UNKNOWN: modelAndView.addObject("dField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("dField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("dField", "green"); }
        switch (userState.getE()) {case LetterState.UNKNOWN: modelAndView.addObject("eField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("eField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("eField", "green"); }
        switch (userState.getF()) {case LetterState.UNKNOWN: modelAndView.addObject("fField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("fField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("fField", "green"); }
        switch (userState.getG()) {case LetterState.UNKNOWN: modelAndView.addObject("gField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("gField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("gField", "green"); }
        switch (userState.getH()) {case LetterState.UNKNOWN: modelAndView.addObject("hField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("hField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("hField", "green"); }
        switch (userState.getI()) {case LetterState.UNKNOWN: modelAndView.addObject("iField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("iField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("iField", "green"); }
        switch (userState.getJ()) {case LetterState.UNKNOWN: modelAndView.addObject("jField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("jField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("jField", "green"); }
        switch (userState.getK()) {case LetterState.UNKNOWN: modelAndView.addObject("kField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("kField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("kField", "green"); }
        switch (userState.getL()) {case LetterState.UNKNOWN: modelAndView.addObject("lField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("lField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("lField", "green"); }
        switch (userState.getM()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("mField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("mField", "green"); }
        switch (userState.getN()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("nField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("nField", "green"); }
        switch (userState.getO()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("oField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("oField", "green"); }
        switch (userState.getQ()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("qField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("qField", "green"); }
        switch (userState.getR()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("rField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("rField", "green"); }
        switch (userState.getS()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("sField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("sField", "green"); }
        switch (userState.getT()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("tField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("tField", "green"); }
        switch (userState.getU()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("uField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("uField", "green"); }
        switch (userState.getV()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("vField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("vField", "green"); }
        switch (userState.getW()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("wField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("wField", "green"); }
        switch (userState.getX()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("xField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("xField", "green"); }
        switch (userState.getY()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("yField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("yField", "green"); }
        switch (userState.getZ()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("zField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("zField", "green"); }
    }

    public static void formToCompState(JottoForm form, Alphabet compState) {
        switch (form.getaField()) {case "green": compState.setA(LetterState.IN_WORD);break;case "red": compState.setA(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setA(LetterState.UNKNOWN); }
        switch (form.getbField()) {case "green": compState.setB(LetterState.IN_WORD);break;case "red": compState.setB(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setB(LetterState.UNKNOWN); }
        switch (form.getcField()) {case "green": compState.setC(LetterState.IN_WORD);break;case "red": compState.setC(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setC(LetterState.UNKNOWN); }
        switch (form.getdField()) {case "green": compState.setD(LetterState.IN_WORD);break;case "red": compState.setD(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setD(LetterState.UNKNOWN); }
        switch (form.geteField()) {case "green": compState.setE(LetterState.IN_WORD);break;case "red": compState.setE(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setE(LetterState.UNKNOWN); }
        switch (form.getfField()) {case "green": compState.setF(LetterState.IN_WORD);break;case "red": compState.setF(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setF(LetterState.UNKNOWN); }
        switch (form.getgField()) {case "green": compState.setG(LetterState.IN_WORD);break;case "red": compState.setG(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setG(LetterState.UNKNOWN); }
        switch (form.gethField()) {case "green": compState.setH(LetterState.IN_WORD);break;case "red": compState.setH(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setH(LetterState.UNKNOWN); }
        switch (form.getiField()) {case "green": compState.setI(LetterState.IN_WORD);break;case "red": compState.setI(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setI(LetterState.UNKNOWN); }
        switch (form.getjField()) {case "green": compState.setJ(LetterState.IN_WORD);break;case "red": compState.setJ(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setJ(LetterState.UNKNOWN); }
        switch (form.getkField()) {case "green": compState.setK(LetterState.IN_WORD);break;case "red": compState.setK(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setK(LetterState.UNKNOWN); }
        switch (form.getlField()) {case "green": compState.setL(LetterState.IN_WORD);break;case "red": compState.setL(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setL(LetterState.UNKNOWN); }
        switch (form.getmField()) {case "green": compState.setM(LetterState.IN_WORD);break;case "red": compState.setM(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setM(LetterState.UNKNOWN); }
        switch (form.getnField()) {case "green": compState.setN(LetterState.IN_WORD);break;case "red": compState.setN(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setN(LetterState.UNKNOWN); }
        switch (form.getoField()) {case "green": compState.setO(LetterState.IN_WORD);break;case "red": compState.setO(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setO(LetterState.UNKNOWN); }
        switch (form.getpField()) {case "green": compState.setP(LetterState.IN_WORD);break;case "red": compState.setP(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setP(LetterState.UNKNOWN); }
        switch (form.getqField()) {case "green": compState.setQ(LetterState.IN_WORD);break;case "red": compState.setQ(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setQ(LetterState.UNKNOWN); }
        switch (form.getrField()) {case "green": compState.setR(LetterState.IN_WORD);break;case "red": compState.setR(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setR(LetterState.UNKNOWN); }
        switch (form.getsField()) {case "green": compState.setS(LetterState.IN_WORD);break;case "red": compState.setS(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setS(LetterState.UNKNOWN); }
        switch (form.gettField()) {case "green": compState.setT(LetterState.IN_WORD);break;case "red": compState.setT(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setT(LetterState.UNKNOWN); }
        switch (form.getuField()) {case "green": compState.setU(LetterState.IN_WORD);break;case "red": compState.setU(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setU(LetterState.UNKNOWN); }
        switch (form.getvField()) {case "green": compState.setV(LetterState.IN_WORD);break;case "red": compState.setV(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setV(LetterState.UNKNOWN); }
        switch (form.getwField()) {case "green": compState.setW(LetterState.IN_WORD);break;case "red": compState.setW(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setW(LetterState.UNKNOWN); }
        switch (form.getxField()) {case "green": compState.setX(LetterState.IN_WORD);break;case "red": compState.setX(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setX(LetterState.UNKNOWN); }
        switch (form.getyField()) {case "green": compState.setY(LetterState.IN_WORD);break;case "red": compState.setY(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setY(LetterState.UNKNOWN); }
        switch (form.getzField()) {case "green": compState.setZ(LetterState.IN_WORD);break;case "red": compState.setZ(LetterState.NOT_IN_WORD);break;case "black": case "":compState.setZ(LetterState.UNKNOWN); }
    }

    public static void compStateToForm(ModelAndView modelAndView, Alphabet compState) {
        switch (compState.getA()) {case LetterState.UNKNOWN: modelAndView.addObject("aField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("aField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("aField", "green"); }
        switch (compState.getB()) {case LetterState.UNKNOWN: modelAndView.addObject("bField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("bField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("bField", "green"); }
        switch (compState.getC()) {case LetterState.UNKNOWN: modelAndView.addObject("cField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("cField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("cField", "green"); }
        switch (compState.getD()) {case LetterState.UNKNOWN: modelAndView.addObject("dField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("dField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("dField", "green"); }
        switch (compState.getE()) {case LetterState.UNKNOWN: modelAndView.addObject("eField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("eField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("eField", "green"); }
        switch (compState.getF()) {case LetterState.UNKNOWN: modelAndView.addObject("fField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("fField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("fField", "green"); }
        switch (compState.getG()) {case LetterState.UNKNOWN: modelAndView.addObject("gField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("gField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("gField", "green"); }
        switch (compState.getH()) {case LetterState.UNKNOWN: modelAndView.addObject("hField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("hField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("hField", "green"); }
        switch (compState.getI()) {case LetterState.UNKNOWN: modelAndView.addObject("iField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("iField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("iField", "green"); }
        switch (compState.getJ()) {case LetterState.UNKNOWN: modelAndView.addObject("jField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("jField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("jField", "green"); }
        switch (compState.getK()) {case LetterState.UNKNOWN: modelAndView.addObject("kField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("kField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("kField", "green"); }
        switch (compState.getL()) {case LetterState.UNKNOWN: modelAndView.addObject("lField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("lField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("lField", "green"); }
        switch (compState.getM()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("mField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("mField", "green"); }
        switch (compState.getN()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("nField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("nField", "green"); }
        switch (compState.getO()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("oField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("oField", "green"); }
        switch (compState.getQ()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("qField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("qField", "green"); }
        switch (compState.getR()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("rField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("rField", "green"); }
        switch (compState.getS()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("sField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("sField", "green"); }
        switch (compState.getT()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("tField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("tField", "green"); }
        switch (compState.getU()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("uField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("uField", "green"); }
        switch (compState.getV()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("vField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("vField", "green"); }
        switch (compState.getW()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("wField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("wField", "green"); }
        switch (compState.getX()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("xField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("xField", "green"); }
        switch (compState.getY()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("yField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("yField", "green"); }
        switch (compState.getZ()) {case LetterState.UNKNOWN: modelAndView.addObject("mField", "black");break;case LetterState.NOT_IN_WORD: modelAndView.addObject("zField", "red");break;case LetterState.IN_WORD: modelAndView.addObject("zField", "green"); }
    }

    @RequestMapping(value = "/jotto", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView userSubmitsWord(@ModelAttribute JottoForm form) {
        ModelAndView modelAndView = new ModelAndView();
        Word wordExists = wordService.findWordByWord(form.getWord());
        String compGuess = wordService.findRandomWord().getWord();
        Alphabet userState = new Alphabet();
        formToUserState(form, userState);
        gameState.setUser_state(userState);
        userStateToForm(modelAndView, userState);
        Alphabet compState = new Alphabet();
        formToCompState(form, compState);
        gameState.setComp_state(compState);
        compStateToForm(modelAndView, compState);
        if (form.getWord().equalsIgnoreCase(gameState.getCompSecret())){
            modelAndView.addObject("message",   "You won! The Word was: " + form.getWord());
            gameState.getUserGuesses().add(form.getWord());
            gameState.getUserPoints().add(5);
            gameState.getCompGuesses().add("");
            gameState.getCompPoints().add(0);
            modelAndView.setViewName("result");
            System.out.println(gameState.getUserPoints());
            System.out.println(gameState.getCompPoints());
            saveState();
            return modelAndView;
        }
        else if (wordExists == null) {
            modelAndView.addObject("message",  form.getWord() + " is not valid.");
        }

        else {
            int userPoints = getPoints(form.getWord(),gameState.getCompSecret());
            System.out.println(userPoints);
            int random = (int) (Math.random() * wordList.size());
            Word w = wordList.get(random);
            compGuess = w.getWord();
            JottoAI.intelligence(wordList, gameState.getUserSecret(), compGuess);
            int compPoints = getPoints(compGuess,gameState.getUserSecret());
            gameState.getCompGuesses().add(compGuess);
            gameState.getUserGuesses().add(form.getWord());
            gameState.getUserPoints().add(userPoints);
            gameState.getCompPoints().add(compPoints);
            if (compGuess.equalsIgnoreCase(gameState.getUserSecret())) {
                modelAndView.addObject("message",   "You lost! The Word was: " + gameState.getCompSecret());
                saveState();
                modelAndView.setViewName("result");
            }
            else {
                modelAndView.addObject("message",  form.getWord() + " is valid.");
                modelAndView.addObject("form", new JottoForm());
                modelAndView.setViewName("jotto");
            }
        }

        JottoForm newForm = new JottoForm();
        newForm.setaField(form.getaField());
        newForm.setbField(form.getbField());
        newForm.setcField(form.getcField());
        newForm.setdField(form.getdField());
        newForm.seteField(form.geteField());
        newForm.setfField(form.getfField());
        newForm.setgField(form.getgField());
        newForm.sethField(form.gethField());
        newForm.setiField(form.getiField());
        newForm.setjField(form.getjField());
        newForm.setkField(form.getkField());
        newForm.setlField(form.getlField());
        newForm.setmField(form.getmField());
        newForm.setnField(form.getnField());
        newForm.setoField(form.getoField());
        newForm.setpField(form.getpField());
        newForm.setqField(form.getqField());
        newForm.setrField(form.getrField());
        newForm.setsField(form.getsField());
        newForm.settField(form.gettField());
        newForm.setuField(form.getuField());
        newForm.setvField(form.getvField());
        newForm.setwField(form.getwField());
        newForm.setxField(form.getxField());
        newForm.setyField(form.getyField());
        newForm.setzField(form.getzField());
        modelAndView.addObject("form", new JottoForm());

        modelAndView.addObject("userSecret", "This is your secret: " +gameState.getUserSecret());
        System.out.println("cmp secret is"+ gameState.getCompSecret());
        ArrayList<String> updatedUserGuesses = new ArrayList<>();
        for (int i = 0; i < gameState.getUserGuesses().size(); i++) {
            String message = "";
            for (int j = 0; j < 5; j++) {
                message += "<span class=\"letter" + gameState.getUserGuesses().get(i).charAt(j) + "\">"
                        + gameState.getUserGuesses().get(i).charAt(j) + "</span>";

            }
            updatedUserGuesses.add(message);
        }
        modelAndView.addObject("userGuesses",updatedUserGuesses);
        ArrayList<String> updatedCompGuesses = new ArrayList<>();
        for (int i = 0; i < gameState.getCompGuesses().size(); i++) {
            String message = "";
            for (int j = 0; j < 5; j++) {
                message += "<span class=\"compLetter" + gameState.getCompGuesses().get(i).charAt(j) + "\">"
                        + gameState.getCompGuesses().get(i).charAt(j) + "</span>";

            }
            updatedCompGuesses.add(message);
        }
        modelAndView.addObject("compGuesses", updatedCompGuesses);
        modelAndView.addObject("userPoints",gameState.getUserPoints());
        modelAndView.addObject("compPoints",gameState.getCompPoints());
        return modelAndView;
    }


    public int getPoints(String first, String second){
        int points = 0;
        first = first.toLowerCase(); second = second.toLowerCase();
        for (int i=0; i < first.length(); i++){
            if(second.indexOf(first.charAt(i)) > -1)
                points++;
        }
        return points;
    }
    public void saveState(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Game game = gameService.saveGame(gameState,user);
        guessService.saveGuesses(gameState,game);
    }

    public boolean isUnique(String str){
        Set<Character> charSet = new TreeSet<>();
        for( char c : str.toCharArray() ) {
            charSet.add(c);
        }
        return charSet.size() == 5;
    }

//    @RequestMapping(value = "/jotto", method = RequestMethod.POST)
//    public ModelAndView userWordVerifier(@Valid Word word, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        Word wordExists = wordService.findWordByWord(word.getWord());
//        if (wordExists == null) {
//            bindingResult
//                    .rejectValue("word", "error.word",
//                            "The chosen word is not a valid English word.");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("jotto");
//        } else {
//            modelAndView.addObject("word",  word.getWord() + "is valid.");
//            modelAndView.addObject("userWord", word.getWord());
//            modelAndView.setViewName("jotto");
//        }
//        return modelAndView;
//    }
}
