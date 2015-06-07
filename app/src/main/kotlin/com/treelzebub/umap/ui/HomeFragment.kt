package com.treelzebub.umap.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.Discogs
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.util.TokenHolder
import com.treelzebub.umap.util.getUser
import org.joda.time.DateTime
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter

/**
 * Created by Tre Murillo on 6/6/15
 */
public class HomeFragment : Fragment() {

    companion object {
        private val TAG = javaClass<HomeFragment>().getSimpleName()
    }

    val tokenText: TextView by bindView(R.id.token_text)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserNameTextView()
    }

    private fun setUserNameTextView() {
        tokenText.setText(getUser().username)
    }
}