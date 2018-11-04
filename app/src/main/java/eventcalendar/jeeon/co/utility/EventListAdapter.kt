package eventcalendar.jeeon.co.utility

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import eventcalendar.jeeon.co.eventcalendar.R
import kotlinx.android.synthetic.main.each_event_item.view.*

class EventListAdapter internal constructor(
        context: Context, val clickListener: (Event) -> Unit
    ) : RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>()
    private var allEvents = emptyList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTitleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        val eventDetailsTextView: TextView = itemView.findViewById(R.id.eventDetailsTextView)
        fun bind(event: Event, clickListener: (Event) -> Unit) {
            itemView.eventTitleTextView.text = event.title
            itemView.eventDetailsTextView.text = event.details
            itemView.setOnClickListener { clickListener(event)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = inflater.inflate(R.layout.each_event_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], clickListener)
    }

    internal fun setEvents(events: List<Event>, selectedTime : Long?) {
        //filter with selected time
        var selectedDateEventList = arrayListOf<Event>()
        for (event: Event in events){
            Log.e("Live Refresh", "EventTime: "+ event.event_date+ " selected Time: "+selectedTime)
            if (event.event_date == selectedTime){
                Log.e("Live Refresh", "Match")
                selectedDateEventList.add(event)
            }
        }
        this.allEvents = events
        this.events = selectedDateEventList
        notifyDataSetChanged()
    }
    internal fun loadSelectedDayEvents(selectedTime : Long?) {
        //filter with selected time
        var selectedDateEventList = arrayListOf<Event>()
        for (event: Event  in this.allEvents){
            Log.e("Manual Refresh", "EventTime: "+ event.event_date+ " selected Time: "+selectedTime)
            if (event.event_date == selectedTime){
                Log.e("Manual Refresh", "Match")
                selectedDateEventList.add(event)
            }
        }
        this.events = selectedDateEventList

        notifyDataSetChanged()
    }



    override fun getItemCount() = events.size
}