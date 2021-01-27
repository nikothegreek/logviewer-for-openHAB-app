package com.cyb3rko.logviewerforopenhab.fragments

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cyb3rko.logviewerforopenhab.BuildConfig
import com.cyb3rko.logviewerforopenhab.R
import com.mikepenz.aboutlibraries.LibsBuilder
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val nightMode = when (requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            Configuration.UI_MODE_NIGHT_UNDEFINED -> false
            else -> false
        }

        return AboutPage(context)
            .enableDarkMode(nightMode)
            .setImage(R.mipmap.ic_launcher_foreground)
            .setDescription(getString(R.string.about_description))
            .addItem(
                Element().setTitle(String.format(getString(R.string.about_element_version), BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE))
                    .setIconDrawable(R.drawable.about_icon_github).setOnClickListener(showChangelog())
            )
            .addGroup(getString(R.string.about_group_legal))
            .addItem(
                Element().setTitle(getString(R.string.about_element_libraries)).setIconDrawable(R.drawable._ic_libraries)
                    .setOnClickListener(showLibraries())
            )
            .addItem(
                Element().setTitle(getString(R.string.about_element_icons)).setIconDrawable(R.drawable._ic_question).setOnClickListener(showIcons())
            )
            .addItem(
                Element().setTitle(getString(R.string.about_element_animations)).setIconDrawable(R.drawable._ic_question)
                    .setOnClickListener(showAnimations())
            )
            .addGroup(getString(R.string.about_group_connect))
            .addItem(
                Element().setTitle(getString(R.string.about_element_feedback_text)).setIconDrawable(R.drawable.about_icon_github)
                    .setOnClickListener(openOnGithub())
            )
            .addEmail(getString(R.string.about_element_email_value), getString(R.string.about_element_email_text))
//            .addWebsite(getString(R.string.about_element_website_value), getString(R.string.about_element_website_text))
            .addItem(
                Element().setTitle(getString(R.string.about_element_youtube_text)).setIconDrawable(R.drawable.about_icon_youtube)
                    .setIconTint(R.color.about_youtube_color).setOnClickListener(openYouTubeProfile())
            )
            .addGitHub(getString(R.string.about_element_github_value), getString(R.string.about_element_github_text))
            .addItem(
                Element().setTitle(getString(R.string.about_element_instagram_text)).setIconDrawable(R.drawable.about_icon_instagram)
                    .setIconTint(R.color.about_instagram_color).setOnClickListener(openInstaPage())
            )
            .create()
    }

    private fun openYouTubeProfile(): View.OnClickListener? {
        return View.OnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_element_youtube_value)))) }
    }

    private fun showChangelog(): View.OnClickListener? {
        return View.OnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_changelog_link)))) }
    }

    private fun showLibraries(): View.OnClickListener? {
        return View.OnClickListener {
            context?.let { trueContext ->
                LibsBuilder()
                    .withShowLoadingProgress(true)
                    .withAboutVersionShownCode(false)
                    .withAboutVersionShownName(false)
                    .withAutoDetect(true)
                    .withAboutIconShown(false)
                    .withAboutVersionShown(false)
                    .withVersionShown(true)
                    .withLicenseDialog(true)
                    .withLicenseShown(true)
                    .withCheckCachedDetection(true)
                    .withSortEnabled(true)
                    .start(trueContext)
            }
        }
    }

    private fun showIcons(): View.OnClickListener? {
        return View.OnClickListener { findNavController().navigate(R.id.nav_about_icons) }
    }

    private fun showAnimations(): View.OnClickListener? {
        return View.OnClickListener { findNavController().navigate(R.id.nav_about_animations) }
    }

    private fun openOnGithub(): View.OnClickListener? {
        return View.OnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_element_feedback_value)))) }
    }

    private fun openInstaPage(): View.OnClickListener? {
        return View.OnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_element_instagram_value)))) }
    }
}