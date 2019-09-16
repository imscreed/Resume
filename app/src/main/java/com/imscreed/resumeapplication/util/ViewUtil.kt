package com.imscreed.resumeapplication.util

import android.content.Context
import android.text.SpannedString
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.scale
import com.imscreed.resumeapplication.R
import com.imscreed.resumeapplication.model.data.ResumeDataModel
import java.text.SimpleDateFormat
import java.util.*

class ViewUtil {
    companion object{
        fun getStylizedResume(context: Context, resumeDataModel: ResumeDataModel) : SpannedString {
            val user = resumeDataModel.basics

            val name = user.name
            val nameParts = name.split(" ")
            val firstName = nameParts[0]
            val lastName = nameParts[1]

            val stylizedString: SpannedString = buildSpannedString {

                color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                    scale(1.75f) {
                        bold {
                            append(firstName)
                        }
                        append(" $lastName")
                    }
                }

                scale(1.25f) {
                    append("\n")
                    append(user.label)
                }
                append("\n\n\n")
                scale(1.25f) {
                    bold {
                        color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                            append("Summary".toUpperCase())
                        }
                    }
                }
                append("\n\n")
                append(user.summary)


                //Skill
                append("\n\n\n")
                scale(1.25f) {
                    bold {
                        color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                            append("Skills".toUpperCase())
                        }
                    }
                }
                resumeDataModel.skills.forEach { skill ->
                    append("\n\n")

                    color(ContextCompat.getColor(context, R.color.black)) {
                        bold { append(skill.name) }
                    }
                    skill.keywords.forEach { name ->
                        append("\n")
                        append("- $name")
                    }
                }

                append("\n\n\n")
                scale(1.25f) {
                    bold {
                        color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                            append("Work".toUpperCase())
                        }
                    }
                }
                resumeDataModel.work.forEach { work ->
                    append("\n\n")
                    color(ContextCompat.getColor(context, R.color.black)) {
                        bold { append(work.position) }
                    }
                    append("\n")
                    bold { append(work.company) }
                    append("\n")
                    append(work.website)
                    if (work.startDate.isNullOrEmpty().not() && work.endDate.isNullOrEmpty().not()) {
                        append("\n")
                        append("${work.startDate.formatDate()} - ${work.endDate.formatDate()}")
                    }
                    append("\n\n")
                    append(work.summary)

                    if (work.highlights.isNullOrEmpty().not()) {
                        append("\n\n")
                        bold {
                            append("Highlights")
                        }

                        work.highlights.forEach {
                            append("\n")
                            append("- $it")
                        }
                    }
                }

                append("\n\n\n")
                scale(1.25f) {
                    bold {
                        color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                            append("Education".toUpperCase())
                        }
                    }
                }
                resumeDataModel.education.forEach { education ->
                    append("\n\n")
                    color(ContextCompat.getColor(context, R.color.black)) {
                        bold { append(education.institution) }
                    }
                    append("\n")
                    scale(.75f) {
                        append("${education.startDate.formatDate()} - ${education.endDate.formatDate()}")
                    }
                    append("\n")
                    color(ContextCompat.getColor(context, R.color.black)) {
                        append(education.area)
                    }
                    append("\n")
                    append(education.studyType)
                    append("\n\n\n")
                    scale(1.25f) {
                        bold {
                            color(ContextCompat.getColor(context, R.color.colorPrimary)) {
                                append("Links".toUpperCase())
                            }
                        }
                    }
                    append("\n\n")
                    bold {
                        append("email".toUpperCase())
                    }
                    append("\n")
                    append(user.email)
                    append("\n\n")
                    bold {
                        append("website".toUpperCase())
                    }
                    append("\n")
                    append(user.website)

                    user.profiles.forEach {
                        append("\n\n")
                        bold {
                            append(it.network.toUpperCase())
                        }
                        append("\n")
                        append(it.url)
                    }
                }
            }
            return stylizedString
        }

        fun String.formatDate(): String {
            val originalDate = SimpleDateFormat("yyyy-MM-DD", Locale.getDefault()).parse(this)
            return SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(originalDate)
        }
    }


}