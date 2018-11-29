import java.util.ArrayList;

public class TaskList
{
	private ArrayList<Task> Tasks = new ArrayList<Task>();
	
	public void addTask(Task task) {
		Tasks.add(task);
	}
	public void removeTaskById(int id) {
		Tasks.remove(id);
	}
	public int getTaskCount() {
		return Tasks.size();
	}
	
	
	
}
