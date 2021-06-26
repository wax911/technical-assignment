package za.co.itschools.dev.data.enrolment.converters

import timber.log.Timber
import za.co.itschools.dev.data.core.mapper.AbstractMapper
import za.co.itschools.dev.data.enrolment.model.EnrolmentModel
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import java.text.SimpleDateFormat
import java.util.*

internal typealias EnrolmentConverter = AbstractMapper<List<EnrolmentModel>, List<Enrolment>>

internal class EnrolmentModelConverter(
    override val fromType: (List<EnrolmentModel>) -> List<Enrolment> = ::transform
) : EnrolmentConverter() {
    private companion object {

        private fun Long.asDateTime(): String {
            return try {
                val formatter = SimpleDateFormat(
                    "dd MMMM yyyy HH:mm",
                    Locale.getDefault()
                )
                val date = Date(toLong() * 1000)
                formatter.format(date)
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        }

        fun transform(model: List<EnrolmentModel>): List<Enrolment> {
            val results = mutableListOf<Enrolment>()

            val grouping = model.groupBy(EnrolmentModel::grade)

            for (entry in grouping.entries) {
                results.add(Enrolment.Header(entry.key))

                val items = entry.value.map {
                    Enrolment.Item(
                        groupId = it.groupId,
                        id = it.id,
                        code = it.code,
                        grade = it.grade,
                        name = it.name,
                        private = it.private,
                        updated = it.updated?.asDateTime(),
                        created = it.created.asDateTime(),
                        year = it.year,
                        language = it.language,
                        source = it.source,
                        root = it.root,
                        metadata = it.metadata,
                        deleted = it.deleted,
                        deletedDate = it.deletedDate?.asDateTime(),
                        owner = it.owner,
                        updatedBy = it.updatedBy,
                        deletedBy = it.deletedBy,
                        subject = it.subject,
                        start = it.start.asDateTime(),
                        end = it.end.asDateTime(),
                        systemGroup = it.systemGroup,
                        memberParents = it.memberParents,
                        educatorControl = it.educatorControl,
                        subjectId = it.subjectId,
                    )
                }
                results.addAll(items)
            }

            return results
        }
    }
}