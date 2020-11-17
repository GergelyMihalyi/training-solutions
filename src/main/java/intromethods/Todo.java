package intromethods;

public class Todo {
    private boolean finished;
    private String caption;

    public Todo(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
    public boolean isFinished(){
        return finished;
    }
    public void finish() {
        finished = true;
    }

}
