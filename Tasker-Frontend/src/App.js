import Header from "./components/Header";
import Tasks from "./components/Tasks";
import { useState, useEffect } from 'react'
import AddTask from "./components/AddTask";

function App() {
  const [showAddTask, setShowAddTask] = useState(false)

  const [tasks, setTasks] = useState([])

  useEffect(() => {
    // GET request using fetch inside useEffect React hook
    fetch('http://localhost:8080/task/all')
        .then(response => response.json())
        .then(data => {setTasks(data);console.log(data);console.log(tasks)});
// empty dependency array means this effect will only run once (like componentDidMount in classes)
});


  // Add Task
  const addTask = (task) => {
    console.log(task);
  }
  // Delete Task
  const deleteTask = (id) => {
    // setTasks(tasks.filter((task) => task.id !== id));
    fetch('http://localhost:8080/task/' + id, { method: 'DELETE' })
  }

  // Toggle Reminder
  const toggleReminder = (id) => {
    setTasks(tasks.map((task) => 
      task.id == id ? { ...task, reminder: !task.reminder } : task
    ))
  }

  return (
    <div className="container">
      <Header 
      onAdd = {() => setShowAddTask(!showAddTask)} 
      showAdd = {showAddTask}
      />
      {showAddTask && <AddTask onAdd = {addTask}/>}
      {tasks.length > 0 ?
        <Tasks
          tasks={tasks}
          onDelete={deleteTask}
          onToggle={toggleReminder}
        />
        : 'No Tasks'}
    </div>
  );
}


export default App;
