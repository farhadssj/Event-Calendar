package eventcalendar.jeeon.co.utility

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import eventcalendar.jeeon.co.eventcalendar.R

class EventListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTitleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        val eventDetailsTextView: TextView = itemView.findViewById(R.id.eventDetailsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = inflater.inflate(R.layout.each_event_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        holder.eventTitleTextView.text = current.title
        holder.eventDetailsTextView.text = current.details
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount() = events.size
}