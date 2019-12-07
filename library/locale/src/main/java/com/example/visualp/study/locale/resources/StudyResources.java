package com.example.visualp.study.locale.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class StudyResources {

  @Path("test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response parallelStreamTest() throws Exception {

    // 【AcceptLanguage】
    // 最優先されるものについては RFC でも規定しない方向になっている。
    // ※ アプリケーションが独自に決めるものというスタンス。
    // ※ もし、AcceptLanguage から言語が判断つかない場合、一般的に無視 (AcceptLanguage なしと看做) すらしい。
    //
    // 【Java Locale parse】
    // 指定した AcceptLanguage 内にある Language Subtag をパースし、
    // 重みづけを考慮したソートを行った結果が返却される。
    // また、同じ国を示す Language Subtag がある場合は、それも追加して返却される。
    // (参照) https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry 上に存在する
    //
    // 例: iw,en-us;q=0.7,en;q=0.3 の場合、iw が指す国が Hebrew で、新たに定義された同一国の Subtag (he) も返却されるため、
    //     結果は iw(1.0), he(1.0), en-us(0.7), en(0.3) となる。
    List<LanguageRange> ranges = Locale.LanguageRange.parse(
        "Accept-Language: *-TW, en, zh-TW, zh-HK, jp"
    );

    // 【Java Locale filter】
    // Locale 内に存在するものだけに絞り込む。
    // ※ 詳細は PR コメントに書く。
    List<Locale> locales = Locale.filter(
        ranges,
        Arrays.asList(
            Locale.forLanguageTag("zh-Hant-TW"),
            new Locale("en"),
            new Locale("en", "US"),
            new Locale("zh", "TW"),
            new Locale("zh")
        )
    );

    return Response
        .noContent()
        .build();
  }
}
