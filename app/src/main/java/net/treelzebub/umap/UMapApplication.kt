package net.treelzebub.umap

import android.app.Application
import net.treelzebub.umap.inject.ContextInjection
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Tre Murillo on 6/6/15
 */
class UMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ContextInjection.c = this

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quattrocento-Sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}