import { FaTimes } from 'react-icons/fa'

const Task = ({ task, onDelete }) => {
    return (
        <div className={`task ${task.reminder? 'reminder' : ''}`}
             Onclick = {task.id}>
            <h3>
                {task.title}
                <FaTimes
                    style={{ color: 'red', cursor: 'pointer' }}
                    onClick={() => onDelete(task.id)}
                />
            </h3>
            <p>{task.date}</p>
        </div >
    )
};

export default Task;
