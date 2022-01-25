import { useState, useEffect } from "react";
const AddTask = ({onAdd}) => {
    const [title, setText] = useState('');
    const [date, setDay] = useState('');
    const [reminder, setReminder] = useState(false);
    async function fetchData() {
                // POST request using fetch inside useEffect React hook
                const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ title: title, date:date, reminder: reminder})
                };
                fetch('http://localhost:8080/task', requestOptions)
                    .then(response => response.json());
            // empty dependency array means this effect will only run once (like componentDidMount in classes)
    }

    const onSubmit = (e) => {
        e.preventDefault()
        
        if(!title) {
            alert('Please add a task')
            return
        }
        fetchData()
        // onAdd( { title, date, reminder})

        setText('')
        setDay('')
        setReminder(false)
    }
    return (
        <form className='add-form' onSubmit = {onSubmit}>
            <div className="form-control">
                <label>Task</label>
                <input type='text' placeholder='Add Task'
                    value={title} onChange={(e) => setText(e.target.value)} />
            </div>
            <div className="form-control">
                <label>Day & Time</label>
                <input type='text' placeholder='Add Day & Time'
                    value={date} 
                    onChange={(e) => setDay(e.target.value)} />
            </div>
            <div className='form-control form-control-check'>
                <label>Set Reminder</label>
                <input 
                type='checkbox' 
                checked = {reminder}
                value = {reminder} 
                onChange = {(e) => setReminder(e.currentTarget.checked)}
                />
            </div>
            <input type='submit' value='Save Task' className='btn btn-block' />
        </form>
    )
};

export default AddTask;
