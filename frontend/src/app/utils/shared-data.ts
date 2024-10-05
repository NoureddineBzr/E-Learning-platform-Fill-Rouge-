import { profileImagesUrls } from "../constants/constants";
import { User } from "../model/user,model";

export function getAuthUser(){

    let data = JSON.parse(localStorage.getItem('AUTH_USER'));

    const user:User ={
        id: data.id,
        firstName: data.firstName,
        lastName: data.lastName,
        profileImage: profileImagesUrls+data.profileImage,
        username: data.username,
        password:null,

    };

   return user;
}