package manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import entity.Agenda;
import persistence.AgendaDao;

import org.primefaces.model.ScheduleEvent;

@ManagedBean
@ViewScoped
public class AgendaBean {
	
	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
	
	private Agenda agenda;
	
	private List<Agenda> agendalista;
	
	private boolean slotEventOverlap = true;
    private boolean showWeekNumbers = false;
    private boolean showHeader = true;
    private boolean draggable = true;
    private boolean resizable = true;
    private boolean selectable = false;
    private boolean showWeekends = true;
    private boolean tooltip = true;
    private boolean allDaySlot = true;
    private boolean rtl = false;

    private double aspectRatio = Double.MIN_VALUE;

    private String leftHeaderTemplate = "prev,next today";
    private String centerHeaderTemplate = "title";
    private String rightHeaderTemplate = "dayGridMonth,timeGridWeek,timeGridDay,listYear";
    private String nextDayThreshold = "09:00:00";
    private String weekNumberCalculation = "local";
    private String weekNumberCalculator = "date.getTime()";
    private String displayEventEnd;
    private String timeFormat;
    private String slotDuration = "00:30:00";
    private String slotLabelInterval;
    private String slotLabelFormat = "HH:mm";
    private String scrollTime = "06:00:00";
    private String minTime = "04:00:00";
    private String maxTime = "20:00:00";
    private String locale = "en";
    private String serverTimeZone = ZoneId.systemDefault().toString();
    private String timeZone = "";
    private String clientTimeZone = "local";
    private String columnHeaderFormat = "";
    private String view = "timeGridWeek";
    private String height = "auto";

    private String extenderCode = "// Write your code here or select an example from above";
    private String selectedExtenderExample = "";
	
	public AgendaBean() {
		agenda = new Agenda();
		
		eventModel = new DefaultScheduleModel();
		
		try {
			
			java.util.Date data = new java.util.Date();
			
			SimpleDateFormat mes = new SimpleDateFormat("MM");
			SimpleDateFormat ano = new SimpleDateFormat("yyyy");
			
			String dataS = "01/" + mes.format(data) + "/" + ano.format(data);
			
			AgendaDao ad = new AgendaDao();
			
			agendalista = ad.buscagendadomes(dataS);
			
			for (Agenda ag : agendalista) {
				adicionarAgendamento(ag);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}

        //addEvents2EventModel(LocalDateTime.now());
        //addEvents2EventModel(LocalDateTime.now().minusMonths(6));

	}

	public List<Agenda> getAgendalista() {
		return agendalista;
	}

	public void setAgendalista(List<Agenda> agendalista) {
		this.agendalista = agendalista;
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
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private void adicionarAgendamento(Agenda ag) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(df.parse(ag.getData_agenda()));
			
			LocalDateTime data = convertToLocalDateTimeViaInstant(cal.getTime());
			
			DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
	                .title(ag.getNome_ctepac())
	                .startDate(data)
	                .endDate(data)
	                .description(ag.getId_agenda().toString())
	                .borderColor("orange")
	                .build();
	        eventModel.addEvent(event);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		
	}

    private void addEvents2EventModel(LocalDateTime referenceDate) {
        DefaultScheduleEvent<?> event = DefaultScheduleEvent.builder()
                .title("Champions League Match")
                .startDate(previousDay8Pm(referenceDate))
                .endDate(previousDay11Pm(referenceDate))
                .description("Team A vs. Team B")
                .url("https://www.uefa.com/uefachampionsleague/")
                .borderColor("orange")
                .build();
        eventModel.addEvent(event);

        event = DefaultScheduleEvent.builder()
                .startDate(referenceDate.minusDays(6))
                .endDate(referenceDate.minusDays(3))
                .overlapAllowed(true)
                .editable(false)
                .resizable(false)
 //               .display(ScheduleDisplayMode.BACKGROUND)
                .backgroundColor("lightgreen")
                .build();
        eventModel.addEvent(event);

        event = DefaultScheduleEvent.builder()
                .title("Birthday Party")
                .startDate(today1Pm(referenceDate))
                .endDate(today6Pm(referenceDate))
                .description("Aragon")
                .overlapAllowed(true)
                .borderColor("#CB4335")
                .build();
        eventModel.addEvent(event);

        event = DefaultScheduleEvent.builder()
                .title("Breakfast at Tiffanys (always resizable)")
                .startDate(nextDay9Am(referenceDate))
                .endDate(nextDay11Am(referenceDate))
                .description("all you can eat")
                .overlapAllowed(true)
                .resizable(true)
                .borderColor("#27AE60")
                .build();
        eventModel.addEvent(event);

        event = DefaultScheduleEvent.builder()
                .title("Plant the new garden stuff (always draggable)")
                .startDate(theDayAfter3Pm(referenceDate))
                .endDate(fourDaysLater3pm(referenceDate))
                .description("Trees, flowers, ...")
                .draggable(true)
                .borderColor("#27AE60")
                .build();
        eventModel.addEvent(event);

        DefaultScheduleEvent<?> scheduleEventAllDay = DefaultScheduleEvent.builder()
                .title("Holidays (AllDay)")
                .startDate(sevenDaysLater0am(referenceDate))
                .endDate(eightDaysLater0am(referenceDate))
                .description("sleep as long as you want")
                .borderColor("#27AE60")
                .allDay(true)
                .build();
        eventModel.addEvent(scheduleEventAllDay);
    }

    public void addEvent() {
        if (event.isAllDay()) {
            // see https://github.com/primefaces/primefaces/issues/1164
            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
                event.setEndDate(event.getEndDate().plusDays(1));
            }
        }

        if (event.getId() == null) {
            eventModel.addEvent(event);
        }
        else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent<>();
    }

    public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
    	
    	event = selectEvent.getObject();
        
        try {
			
			AgendaDao ad = new AgendaDao();
			
			agenda = ad.buscagendaporid(event.getDescription());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
    }

    public void onViewChange(SelectEvent<String> selectEvent) {
        view = selectEvent.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "View Changed", "View:" + view);
        addMessage(message);
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
    	
    	event = DefaultScheduleEvent.builder()
                .startDate(selectEvent.getObject())
                .endDate(selectEvent.getObject().plusHours(1))                
                .build();
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
                "Delta:" + event.getDeltaAsDuration());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
                "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

        addMessage(message);
    }

    public void onEventDelete() {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");
        if (event != null) {
            ScheduleEvent<?> event = eventModel.getEvent(eventId);
            eventModel.deleteEvent(event);
        }
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean isShowWeekends() {
        return showWeekends;
    }

    public void setShowWeekends(boolean showWeekends) {
        this.showWeekends = showWeekends;
    }

    public boolean isSlotEventOverlap() {
        return slotEventOverlap;
    }

    public void setSlotEventOverlap(boolean slotEventOverlap) {
        this.slotEventOverlap = slotEventOverlap;
    }

    public boolean isShowWeekNumbers() {
        return showWeekNumbers;
    }

    public void setShowWeekNumbers(boolean showWeekNumbers) {
        this.showWeekNumbers = showWeekNumbers;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isTooltip() {
        return tooltip;
    }

    public void setTooltip(boolean tooltip) {
        this.tooltip = tooltip;
    }

    public boolean isRtl() {
        return rtl;
    }

    public void setRtl(boolean rtl) {
        this.rtl = rtl;
    }

    public boolean isAllDaySlot() {
        return allDaySlot;
    }

    public void setAllDaySlot(boolean allDaySlot) {
        this.allDaySlot = allDaySlot;
    }

    public double getAspectRatio() {
        return aspectRatio == 0 ? Double.MIN_VALUE : aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getLeftHeaderTemplate() {
        return leftHeaderTemplate;
    }

    public void setLeftHeaderTemplate(String leftHeaderTemplate) {
        this.leftHeaderTemplate = leftHeaderTemplate;
    }

    public String getCenterHeaderTemplate() {
        return centerHeaderTemplate;
    }

    public void setCenterHeaderTemplate(String centerHeaderTemplate) {
        this.centerHeaderTemplate = centerHeaderTemplate;
    }

    public String getRightHeaderTemplate() {
        return rightHeaderTemplate;
    }

    public void setRightHeaderTemplate(String rightHeaderTemplate) {
        this.rightHeaderTemplate = rightHeaderTemplate;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getNextDayThreshold() {
        return nextDayThreshold;
    }

    public void setNextDayThreshold(String nextDayThreshold) {
        this.nextDayThreshold = nextDayThreshold;
    }

    public String getWeekNumberCalculation() {
        return weekNumberCalculation;
    }

    public void setWeekNumberCalculation(String weekNumberCalculation) {
        this.weekNumberCalculation = weekNumberCalculation;
    }

    public String getWeekNumberCalculator() {
        return weekNumberCalculator;
    }

    public void setWeekNumberCalculator(String weekNumberCalculator) {
        this.weekNumberCalculator = weekNumberCalculator;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(String slotDuration) {
        this.slotDuration = slotDuration;
    }

    public String getSlotLabelInterval() {
        return slotLabelInterval;
    }

    public void setSlotLabelInterval(String slotLabelInterval) {
        this.slotLabelInterval = slotLabelInterval;
    }

    public String getSlotLabelFormat() {
        return slotLabelFormat;
    }

    public void setSlotLabelFormat(String slotLabelFormat) {
        this.slotLabelFormat = slotLabelFormat;
    }

    public String getDisplayEventEnd() {
        return displayEventEnd;
    }

    public void setDisplayEventEnd(String displayEventEnd) {
        this.displayEventEnd = displayEventEnd;
    }

    public String getScrollTime() {
        return scrollTime;
    }

    public void setScrollTime(String scrollTime) {
        this.scrollTime = scrollTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getClientTimeZone() {
        return clientTimeZone;
    }

    public void setClientTimeZone(String clientTimeZone) {
        this.clientTimeZone = clientTimeZone;
    }

    public String getColumnHeaderFormat() {
        return columnHeaderFormat;
    }

    public void setColumnHeaderFormat(String columnHeaderFormat) {
        this.columnHeaderFormat = columnHeaderFormat;
    }

    public String getSelectedExtenderExample() {
        return selectedExtenderExample;
    }

    public void setSelectedExtenderExample(String selectedExtenderExample) {
        this.selectedExtenderExample = selectedExtenderExample;
    }

    public String getExtenderCode() {
        return extenderCode;
    }

    public void setExtenderCode(String extenderCode) {
        this.extenderCode = extenderCode;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }

    public void setServerTimeZone(String serverTimeZone) {
        this.serverTimeZone = serverTimeZone;
    }

    private LocalDateTime previousDay8Pm(LocalDateTime referenceDate) {
        return referenceDate.minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime previousDay11Pm(LocalDateTime referenceDate) {
        return referenceDate.minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime today1Pm(LocalDateTime referenceDate) {
        return referenceDate.withHour(13).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime theDayAfter3Pm(LocalDateTime referenceDate) {
        return referenceDate.plusDays(1).withHour(15).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime today6Pm(LocalDateTime referenceDate) {
        return referenceDate.withHour(18).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime nextDay9Am(LocalDateTime referenceDate) {
        return referenceDate.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime nextDay11Am(LocalDateTime referenceDate) {
        return referenceDate.plusDays(1).withHour(11).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime fourDaysLater3pm(LocalDateTime referenceDate) {
        return referenceDate.plusDays(4).withHour(15).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime sevenDaysLater0am(LocalDateTime referenceDate) {
        return referenceDate.plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime eightDaysLater0am(LocalDateTime referenceDate) {
        return referenceDate.plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public LocalDate getInitialDate() {
        return LocalDate.now().plusDays(1);
    }

	
	public void buscar() {
		
	}

}
