package quru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Поиск сериала на hd.zetflix.online")
public class SearchTest {
    @BeforeAll
    static void setUp() {
    //    Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://hd.zetfix.online";
    }

    @ValueSource( strings = {
            "Лунный рыцарь",
            "Ривердейл"
    })
 //   @DisplayName("Поиск Лунный рыцарь на hd.zetflix.online")
    @ParameterizedTest(name = "Поиск {0} на hd.zetflix.online")
    void searchTest(String testData) {
        open("/search.html?do=search");
        $("#searchinput").setValue(testData);
        $("#dosearch").click();
        $$(".sres-text")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);
        // executeJavaScript("$('#vid_vpaut_div').delete");

    }

    @CsvSource(value = {
            "Лунный рыцарь | В сериале от студии Marvel \"Лунный рыцарь 2022\" - В мире существует огромное количество самых разных людей и все они имеют свои особенности, привычки и конечно же болезни. Что касательно этого бывшего морского пехотинца, то он действительно очень хорошо и честно исполнил свой долг перед родиной. Этот парень старался сделать все возможное, для того чтобы помочь своей родине. Надо сказать, что родина оказалась ему совершенно неблагодарна. Дело в",
            "Ривердейл | В основу сюжета молодежного детективного сериала «Ривердэйл» лег известный комикс, популярный среди подростковой аудитории. Действие разворачивается в небольшом городке, жизнь которого протекала спокойно и неторопливо. Но в преддверии очередного учебного года это неторопливое течение нарушилось трагическим событием, — гибелью одного из старшеклассников, Джейсона Блоссома. Это событие стало серьезным потрясением для друга погибшего, Арчи Эндрюса."
    },
    delimiter = '|')
    @ParameterizedTest(name = "Поиск {0} на hd.zetflix.online, ожидает описание: {1}")
    void searchTestWithRightDesc(String showName, String showDescription) {
        open("/search.html?do=search");
        $("#searchinput").setValue(showName);
        $("#dosearch").click();
        $$(".sres-desc")
                .find(Condition.text(showDescription))
                .shouldBe(Condition.visible);
        // executeJavaScript("$('#vid_vpaut_div').delete");

    }

    static Stream<Arguments> methodSourceExampleTest(){
        return Stream.of(
                Arguments.of("/film/"),
                Arguments.of("/serials/"),
                Arguments.of("/cartoons/")
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest(name = "Поиск типа {0} на hd.zetflix.online, ожидает наличие: {1}")
    void methodSourceExampleTest(String showType) {
        open(showType);
    }

}

