import React, { Component, TabLink, TabContent} from 'react'
import Tabs from 'react-bootstrap/Tabs'
import {Tab }from 'react-bootstrap'
import CarManagment from './CarManagement'

export default class AdminPanel extends Component {

    constructor(){
        super()
        this.state = {
           activeTab : 'user'
        }
    }

    handleSelect = (eventKey) => {
        this.setState({
            activeTab : eventKey.target.value
        })
    }


    render() {
        return(
            <div>
                <h2>Welcome to Admin Panel</h2> 
                <Tabs activeKey={this.state.activeTab} id="controlled-tab" onSelect = {() => this.handleSelect}>
                    <Tab eventKey="user" title="User Management"></Tab>
                    <Tab eventKey="car" title="Car Management" onClick={CarManagment.refreshCars}><CarManagment /></Tab>
                    <Tab eventKey="service" title="Service Plan Management"></Tab>
                    <Tab eventKey="addon" title="Add On Management"></Tab>
                    <Tab eventKey="order" title="Order details"></Tab>
                    <Tab eventKey="leardership" title="Leadership"></Tab>
                    <Tab eventKey="promo" title="PromoCode Management" disabled></Tab>
                </Tabs>
                <Tabs renderActiveTabContentOnly = {true}>
                    <TabLink to="user" title="User Management"></TabLink>
                    <TabLink to="car" title="Car Management"></TabLink> 
                    <TabLink to="service"title="Service Plan Management"></TabLink> 
                    <TabLink to="addon" title="Add On Management"></TabLink> 
                    <TabLink to="order"title="Order details"></TabLink>
                    <TabLink to="leardership"title="Leadership"></TabLink>
                    <TabLink to="promo"title="PromoCode Management"></TabLink>

                    <TabContent for="user">User Management</TabContent>
                    <TabContent for="car"><CarManagment /></TabContent> 
                    <TabContent for="service">Service Plan Management</TabContent> 
                    <TabContent for="addon">Add On Management</TabContent> 
                    <TabContent for="order">Order details</TabContent>
                    <TabContent for="leardership">Leadership</TabContent>
                    <TabContent for="promo">PromoCode Management</TabContent>
                </Tabs>



            </div>
        )
    }
      
}