/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

    tableName: 'epn_usuario',
    attributes: {
        cedula: { //nombre atributo
            type: 'string',
            required: true, //por defecto es false
            columnName: 'epn_cedula',
            unique: true, //por defecto es false
            minLength: 10,
            maxLength: 25
        },

        nombre: {
            type: 'string',
            minLength: 3,
            required: true, //por defecto es false
        },
        correo: {
            type: 'string',
            isEmail: true

        },
        estadoCivil: {
            type: 'string',
            isIn: ['Soltero', 'Casado', 'Divorciado', 'Viudo', 'Unión libre'], //Solo estos valores
            defaultsTo: 'Soltero' //valor por defecto
        },
        password: {
            type: 'string',
            regex: /^[a-zA-Z]\w{3,14}$/
        },

        pokemons: { // RELACION DE UNO A MUCHOS (NOMBRE EN PLURAL)
            collection: 'pokemon', //referencia al modelo
            via: 'usuario', //Nombre de la Foreing Key 
        }


    },

};

//APP
//BDD
//MOVIL