'use strict';

// tag::helper[]
const e = React.createElement;
// end::helper[]

// tag::TodoItem[]
class TodoItem extends React.Component {
    render () {
        const { item } = this.props
        const itemSpan = e('p', {key: `item-p-${item}`}, item)
        const deleteButton = e('button',
                               {value: 'delete', key: `item-delete-${item}`, onClick: () => this.props.delete(item)},
                               'Delete')

        return e('li', {key: item},
                 e('span', {className: 'item'}, [itemSpan, deleteButton]))
    }
}
// end::TodoItem[]

// tag::TodoItems[]
class TodoItems extends React.Component {
    createTask (item) {
        return e(TodoItem, {key: item, delete: this.props.delete, item: item}, null)
    }

    render () {
        return e('ul', {key: 'items', className: 'items'}, this.props.tasks.map(x => this.createTask(x)))
    }
}
// end::TodoItems[]

// tag::TodoPanel[]
class TodoPanel extends React.Component {
    constructor(props) { // <1>
        super(props);
        this.state = {
            task: '',
            tasks: []
        };
    }

    handleChange (e) { // <2>
        this.setState({task: e.target.value})
    }

    addItem () { // <3>
        this.setState({
            tasks: [...this.state.tasks, this.state.task],
            task: ''
        })
    }

    deleteItem (item) { // <4>
        this.setState({tasks: this.state.tasks.filter(x => x !== item)})
    }

    render() {  // <5>
        return e('div', {id: 'todo'},
                 e('input', {type: 'text', onChange: (ev) => this.handleChange(ev), value: this.state.task}),
                 e('button', {onClick: () => this.addItem() }, 'Add'),
                 e(TodoItems, {tasks: this.state.tasks, delete: (item) => this.deleteItem(item)})
        );
    }
}
// end::TodoPanel[]

// tag::footer[]
const domContainer = document.querySelector('#like_button_container');
ReactDOM.render(e(TodoPanel), domContainer);
// end::footer[]
