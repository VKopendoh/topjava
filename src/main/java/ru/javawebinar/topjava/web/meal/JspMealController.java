package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String filter(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @RequestMapping(value = {"/create", "/update"}, method = RequestMethod.POST)
    public ModelAndView createOrUpdate(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            create(meal);
        } else {
            update(meal, getId(request));
        }
        return new ModelAndView("redirect:/meals", model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, ModelMap model) {
        service.delete(getId(request), authUserId());
        return new ModelAndView("redirect:/meals", model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Model model) {
        final Meal meal = get(getId(request));

        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
