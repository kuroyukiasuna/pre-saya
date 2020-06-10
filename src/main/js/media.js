const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
    constructor(props){
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.switchModes = this.switchModes.bind(this);
        this.state = {mode: "media", music: false};
    }

    handleClick(type, action){
        client({
            method: 'POST',　
            path: '/media',
            entity: {type, action},
            headers: {'Content-Type': 'application/json'}
        }).done(
            response => {
                //console.log(response.entity);
                if(response.status.code == 200 && this.state.mode === "media"){
                    if(action === "play" || ((action === "prev" || action === "next") && !this.state.music)){
                        var tmp = this.state.music;
                        this.setState({music: !tmp});
                    }
                }

                if(response.status.code == 200 && this.state.mode === "video"){
                    if(action === "play"){
                        var tmp = this.state.music;
                        this.setState({music: !tmp});
                    }
                }
            }
        );
    }

    switchModes(mode){
        this.setState({mode: mode, music: false});
    }

    render() {
        var name = "play";
        if(this.state.music){
            name = "pause";
        }
        var mode = this.state.mode;
            return (
            <div>
                <a href="/">Homepage</a>
                <br />
                <br />
                <div> Mode: {this.state.mode} </div>
                <ModeSwitch
                    cur={this.state.mode}
                    switchModes={this.switchModes} />
                <br />
                <MediaButton
                    name="prev"
                    type={mode}
                    action="prev"
                    sendMediaRequest={this.handleClick} />
                <MediaButton
                    name={name}
                    type={mode}
                    action="play"
                    sendMediaRequest={this.handleClick} />
                <MediaButton
                    name="next"
                    type={mode}
                    action="next"
                    sendMediaRequest={this.handleClick} />
                <br /><br />

                <MediaButton
                    name="UP"
                    type={mode}
                    action="volumn up"
                    sendMediaRequest={this.handleClick} />
                <MediaButton
                    name="DOWN"
                    type={mode}
                    action="volumn down"
                    sendMediaRequest={this.handleClick} />
            </div>
            )
    }
}

class MediaButton extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
     }

    handleClick(){
        this.props.sendMediaRequest(this.props.type, this.props.action);
    }

    render() {
        return (
            <button onClick={this.handleClick}>
                {this.props.name}
            </button>
        );
    }
}

class ModeSwitch extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.state = {cur: this.props.cur};
    }

    handleClick(){
        var mode = ((this.state.cur === "media") ? "video" : "media");
        this.props.switchModes(mode);
        this.setState({cur: mode});
    }

    render() {
       if(this.state.cur === "media"){
            return (
                <div>
                 <button disabled>media</button>
                 <button onClick={this.handleClick}>video</button>
                </div>
            );
        }else{
            return (
                <div>
                 <button onClick={this.handleClick}>media</button>
                 <button disabled>video</button>
                </div>
            );
        }
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('mediaReact')
)