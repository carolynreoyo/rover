import logo from './logo.svg';
import './App.css';
import Header from './Header';
import AppContent from './AppContent';

function App() {
  return (
    <div className="App" data-bs-theme="light"> 
      <header className="App-header">
        <Header pageTitle="Frontend auth with react" logo={logo}/>
        <div className="container-fluid">
          <div className="row">
            <div className="col">
              <AppContent/>
              </div>
            </div>
        </div>
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
      </header>
    </div>
  );
}

export default App;
