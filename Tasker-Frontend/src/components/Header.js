import Button from "./Button";

const Header = ({title, onAdd, showAdd}) => {

  return (
    <header className = 'header'>
        <h1>{title}</h1>
        <Button color = {showAdd ? 'red':'blue'} text = {showAdd ? 'Close':'Add'} onClick={onAdd}/>
    </header>
    );
};


// const headerStyle = {
//     color: 'red',
//     backgroundColor: 'black',
//   }

Header.defaultProps = {
    title : 'Tasker'
}
export default Header;

