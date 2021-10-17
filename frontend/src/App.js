import "./App.css";
import MockyFormContainer from "./components/mockyForm/MockyFormContainer";
import NavBarContainer from "./components/NavBar/NavBarContainer";
import { Redirect, Route, Switch } from "react-router-dom";
import MockyTableContainer from "./components/mockyTable/MockyTableContainer";
import NoMatchRoute from "./components/core/NoMatchRoute/NoMatchRoute";

function App() {
  return (
    <div>
      <NavBarContainer />
      <Switch>
        <Route exact path="/">
          <Redirect to="/form" />
        </Route>
        <Route exact path="/form">
          <MockyFormContainer />
        </Route>
        <Route exact path="/table">
          <MockyTableContainer />
        </Route>
        <Route path="*">
          <NoMatchRoute />
        </Route>
      </Switch>
    </div>
  );
}

export default App;
