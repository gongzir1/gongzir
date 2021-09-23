import faker from 'faker'
const expertList =[
    { "key": 0,
      "avatar" : faker.image.avatar(),
      "name" : faker.name.firstName(),
      "position" : faker.name.jobTitle(),
      "description":faker.name.jobDescriptor()
    },
    {   "key": 1,
        "avatar" : faker.image.avatar(),
        "name" : faker.name.firstName(),
        "position" : faker.name.jobTitle(),
        "description":faker.name.jobDescriptor(),
        "logo":faker.image.image(),
    },
    {   "key": 2,
        "avatar" : faker.image.avatar(),
        "name" : faker.name.firstName(),
        "position" : faker.name.jobTitle(),
        "description":faker.name.jobDescriptor()
    },
    {   "key": 3,
        "avatar" : faker.image.avatar(),
        "name" : faker.name.firstName(),
        "position" : faker.name.jobTitle(),
        "description":faker.name.jobDescriptor()
      },
      {   "key": 4,
      "avatar" : faker.image.avatar(),
      "name" : faker.name.firstName(),
      "position" : faker.name.jobTitle(),
      "description":faker.name.jobDescriptor()
    },
     {   "key": 5,
    "avatar" : faker.image.avatar(),
    "name" : faker.name.firstName(),
    "position" : faker.name.jobTitle(),
    "description":faker.name.jobDescriptor()
  }
]

export default expertList