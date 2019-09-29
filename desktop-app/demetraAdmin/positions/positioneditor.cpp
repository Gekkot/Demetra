#include "positioneditor.h"

#define HEIGHT ((30*2)+5)
#define WIDTH (300)

positionEditor::positionEditor(QWidget *parent) : QWidget(parent)
{


    this->setMinimumSize(WIDTH,HEIGHT);

    QLabel* nameText = new QLabel(this);
    nameText->setGeometry(0,0,100,(HEIGHT/2)-5);
    nameText->setText(tr("Наименование:"));

    int  previously_x = nameText->x()+nameText->width();


    namePositionLineEdit = new QLineEdit(this);
    namePositionLineEdit->setGeometry(previously_x,0,WIDTH*0.75,(HEIGHT/2)-5);
    namePositionLineEdit->setAlignment(Qt::AlignCenter);



    saveButton = new QPushButton(this);
    saveButton->setGeometry(namePositionLineEdit->x()+namePositionLineEdit->width()+5,0,(WIDTH-WIDTH*0.75),(HEIGHT/2)-5);
    saveButton->setText("Сохранить");
    connect(saveButton,SIGNAL(clicked()),this,SLOT(saveButtonClicked()));

    QLabel* costText = new QLabel(this);
    costText->setGeometry(0,(HEIGHT/2),100,(HEIGHT/2)-5);
    costText->setText(tr("Цена:"));

    previously_x = costText->x()+costText->width();

    costPositionLineEdit = new QLineEdit(this);
    costPositionLineEdit->setGeometry(previously_x,(HEIGHT/2),WIDTH*0.75,(HEIGHT/2)-5);
    costPositionLineEdit->setAlignment(Qt::AlignCenter);

}



void positionEditor::saveButtonClicked()
{
    emit changePosition(namePositionLineEdit->text(),costPositionLineEdit->text());
    this->close();
}

