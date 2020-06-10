const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {comicArtists: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/comicArtists'}).done(response => {
            this.setState({comicArtists: response.entity._embedded.comicArtists});
        });
    }


    render() {
    console.log(this.state.comicArtists);
    if(this.state.comicArtists.length != 0){
        return (
        <div>
            <a href="/">Homepage</a>
            <br />
            <br />
            <div>This page is still under construction. For the time being, please try visit: {"  "}
            <a href={this.state.comicArtists[0].link} target="_blank">
            {this.state.comicArtists[0].artistName}
            </a>
            </div>
         </div>
     )
    }else{
        return (
            "loading..."
        )
    }

    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)