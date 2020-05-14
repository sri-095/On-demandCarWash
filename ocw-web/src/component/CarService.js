import axios from 'axios'

class CarService {
    getAllCars(){
        return axios.get('http://localhost:8080/api/carmanagement/fetchAllCars');
    }

}
export default new CarService()