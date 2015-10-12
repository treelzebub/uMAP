package net.treelzebub.umap

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Tre Murillo on 6/6/15
 */
public class UMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quattrocento-Sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}