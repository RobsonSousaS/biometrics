package ads.web2.biometrics.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ads.web2.biometrics.model.Partida;
import ads.web2.biometrics.model.PartidaRepository;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("partida")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepo;

    @GetMapping("cadastrar")
    public ModelAndView cadastrarPartida(Long id) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            Partida p = partidaRepo.getReferenceById(id);
            mv.addObject("partida", p);
        }
        mv.setViewName("partida/cadastrar");
        return mv;
    }

    @PostMapping("cadastrar")
    @ResponseBody
    public String salvar(String local, String inicio, String fim) {
        Partida p = new Partida(null, local, LocalDateTime.parse(inicio), LocalDateTime.parse(fim));
        partidaRepo.save(p);
        return "Foi criada a partida com id: " + p.getId();
    }

    @GetMapping("consultar")
    public ModelAndView consultar(Long id) {
        ModelAndView mv = new ModelAndView();
        Partida p = partidaRepo.getReferenceById(id);
        mv.addObject("partida", p);
        mv.setViewName("partida/exibir");
        return mv;
    }

    @GetMapping
    public ModelAndView obterTodas() {
        ModelAndView mv = new ModelAndView();
        List<Partida> lista = partidaRepo.findAll();
        mv.addObject("lista", lista);
        mv.setViewName("partida/exibirTodas");
        return mv;
    }

    @DeleteMapping
    public String deletarPartida(Long id) {
        partidaRepo.deleteById(id);
        return "redirect:partida";
    }

    @PutMapping("cadastrar")
    @Transactional
    public String atualizarPartida(Long id, String local, String inicio, String fim) {
        Partida p = partidaRepo.getReferenceById(id);
        p.setLocal(local);
        p.setInicio(LocalDateTime.parse(inicio));
        p.setFim(LocalDateTime.parse(fim));
        return "redirect:/partida";
    }
}
