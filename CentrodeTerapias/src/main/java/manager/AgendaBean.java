package manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleModel;

import entity.Agenda;

import org.primefaces.model.ScheduleEvent;

@ManagedBean
@ViewScoped
public class AgendaBean {
	
	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
	
	private Agenda agenda;
	
	public AgendaBean() {
		agenda = new Agenda();

	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	public ScheduleEvent<?> getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent<?> event) {
		this.event = event;
	}
	
	public void buscar() {
		
	}

}
