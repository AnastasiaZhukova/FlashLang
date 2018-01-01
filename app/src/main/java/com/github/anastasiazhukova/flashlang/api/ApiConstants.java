package com.github.anastasiazhukova.flashlang.api;

public interface ApiConstants {

    final class Url {

        public static final String TRANSLATION_URL_TEMPLATE =
                "https://translation.googleapis.com/language/translate/v2" +
                        "?key=%s" +
                        "&source=%s" +
                        "&target=%s" +
                        "&q=%s";
    }

    enum LanguageKeys {
        auto,
        af,
        sq,
        am,
        ar,
        hy,
        az,
        eu,
        be,
        bn,
        bs,
        bg,
        ca,
        ceb,
        zh,
        co,
        hr,
        cs,
        da,
        nl,
        en,
        eo,
        et,
        fi,
        fr,
        fy,
        gl,
        ka,
        de,
        el,
        gu,
        ht,
        ha,
        haw,
        iw,
        hi,
        hmn,
        hu,
        is,
        ig,
        id,
        ga,
        it,
        ja,
        jw,
        kn,
        kk,
        km,
        ko,
        ku,
        ky,
        lo,
        la,
        lv,
        lt,
        lb,
        mk,
        mg,
        ms,
        ml,
        mt,
        mi,
        mr,
        mn,
        my,
        ne,
        no,
        ny,
        ps,
        fa,
        pl,
        pt,
        pa,
        ro,
        ru,
        sm,
        gd,
        sr,
        st,
        sn,
        sd,
        si,
        sk,
        sl,
        so,
        es,
        su,
        sw,
        sv,
        tl,
        tg,
        ta,
        te,
        th,
        tr,
        uk,
        ur,
        uz,
        vi,
        cy,
        xh,
        yi,
        yo,
        zu,
    }

}
