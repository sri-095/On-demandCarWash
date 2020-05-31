import React, { Component } from 'react'
import CarService from './CarService'

export default class CarManagment extends Component {

    constructor(props) {
        super(props)
        this.state = {
            cardetails: []
        }
        this.refreshCars = this.refreshCars.bind(this)
    }

    componentDidMount() {
        this.refreshCars();
    }

    refreshCars() {
        CarService.getAllCars()
            .then(
                response => {
                    console.log(response);
                    this.setState({ cardetails: response.data })
                }
            )

    }

    render() {
        return (
            <div>
                <table className="table">
                    <tr>
                        <th>Car Number</th>
                        <th>Color</th>
                        <th>Car Name</th>
                    </tr>
                    <tbody>
                        {
                            this.state.cardetails.map(
                                cardetail =>
                                    <tr key={cardetail.plateNumber}>
                                        <td>{cardetail.plateNumber}</td>
                                        <td>{cardetail.color}</td>
                                        <td>{cardetail.name}</td>
                                    </tr>

                            )
                        }
                    </tbody>
                </table>

            </div>

        )
    }

}