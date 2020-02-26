import React from 'react';
import './App.css';
import { Container } from 'reactstrap';
import { AccountListPage } from './components/AccountListPage';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import AccountTransaction from './components/AccountTransaction';
import PaymentPage from './components/PaymentPage';
import { Layout, Menu } from 'antd';
import AccountContextProvider from './contexts/AccountContext';

const { Header, Content, Footer } = Layout;

function App() {
  return (
    <Router>
      <Layout className='layout'>
        <Header>
          <Menu
            theme='dark'
            mode='horizontal'
            defaultSelectedKeys={['1']}
            style={{ lineHeight: '64px' }}
          >
            <Menu.Item key='1'>
              <Link to='/'>Account Page</Link>
            </Menu.Item>
            <Menu.Item key='2'>
              <Link to='/payment'> Payment Page</Link>
            </Menu.Item>
          </Menu>
        </Header>
        <Content style={{ padding: '0 50px' }}>
          <Container className='pt-1'>
            <div className='App'>
              <AccountContextProvider>
                <Switch>
                  <Route exact path='/'>
                    <AccountListPage />
                  </Route>
                  <Route path='/payment'>
                    <PaymentPage />
                  </Route>
                  <Route path='/transaction/:id'>
                    <AccountTransaction />
                  </Route>
                </Switch>
              </AccountContextProvider>
            </div>
          </Container>
        </Content>
        <Footer className='footer'>
          Amigo Payment APP ©2018 Created by Amigo Dude
        </Footer>
      </Layout>
    </Router>
  );
}
export default App;
