
INSERT INTO restaurant (id, name, description, cuisine_type, average_check, rating) VALUES
  (1, 'La Trattoria', 'Traditional Italian dishes with a modern twist.', 'ITALIAN', 45.00, 4.5),
  (2, 'Sakura Sushi', 'Authentic Japanese sushi and sashimi.', 'ASIAN', 55.00, 4.8),
  (3, 'Tbilisi House', 'Warm Georgian hospitality with khinkali and khachapuri.', 'RUSSIAN', 30.00, 4.6),
  (4, 'Le Gourmet', 'Fine French dining experience.', 'EUROPEAN', 75.00, 4.9),
  (5, 'El Sombrero', 'Colorful Mexican cantina with tacos and tequila.', 'ASIAN', 35.00, 4.4);


INSERT INTO visitor (id, name, age, gender) VALUES
  (1, 'Alice Johnson', 28, 'Female'),
  (2, 'Bob Smith', 35, 'Male'),
  (3, 'Carlos Diaz', 22, 'Male'),
  (4, 'Diana Petrova', 31, 'Female'),
  (5, 'Emily Zhang', 26, 'Female'),
  (6, 'Farid Aliyev', 40, 'Male'),
  (7, 'Greta Lorenz', 33, 'Non-binary');

INSERT INTO review (id, visitor_id, restaurant_id, rating, comment) VALUES
  (1, 1, 1, 5, 'Fantastic pasta and wine selection!'),
  (2, 2, 2, 5, 'Very fresh fish and friendly staff.'),
  (3, 3, 3, 4, 'Great khachapuri but the music was too loud.'),
  (4, 4, 4, 5, 'Exquisite presentation and flavors!'),
  (5, 5, 5, 4, 'The tacos were a bit spicy but amazing.'),
  (6, 6, 4, 3, 'Too expensive for the portion size.'),
  (7, 7, 1, 5, 'I loved the ambiance and the service.'),
  (8, 1, 2, 4, 'Sushi rolls were very creative.'),
  (9, 2, 3, 5, 'Delicious food and cozy interior.'),
  (10, 3, 4, 5, 'The desserts were out of this world.');