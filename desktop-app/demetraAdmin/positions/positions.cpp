#include "positions.h"

#define POSITION_HEIGHT (50)
#define POSITION_WIDTH (400)

positions::positions(QWidget *parent, QString name, QString cost) : QWidget(parent)
{
    this->setMinimumSize(POSITION_WIDTH,POSITION_HEIGHT);

    categoryID= "NULL";

    int shift = 25;

    iconPath = "NULL";
    iconLabel = new QLabel(this);
    iconLabel->setGeometry(0,POSITION_HEIGHT/10,POSITION_WIDTH*0.2,
                           POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));

    int previously_x = iconLabel->x()+iconLabel->width();

    nameLabel = new QLabel(this);
    nameLabel->setGeometry(previously_x,POSITION_HEIGHT/10,POSITION_WIDTH*0.6,
                           POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));
    nameLabel->setText(name);
    nameLabel->setAlignment(Qt::AlignCenter);

    QLabel* nameText = new QLabel(this);
    nameText->setText(tr("Наименование"));
    nameText->setGeometry(nameLabel->x(),0,POSITION_WIDTH*0.6,
                           20);
    nameText->setAlignment(Qt::AlignCenter);

    previously_x = nameLabel->x()+nameLabel->width();

    costLabel = new QLabel(this);
    costLabel->setText(cost);
    costLabel->setGeometry(previously_x,POSITION_HEIGHT/10,(POSITION_WIDTH-(POSITION_WIDTH*0.75))/2,
                           POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));

    QLabel* costText = new QLabel(this);
    costText->setText(tr("Цена"));
    costText->setGeometry(costLabel->x(),0,(POSITION_WIDTH-(POSITION_WIDTH*0.75))/2,
                           20);

    previously_x = costLabel->x()+costLabel->width();

    editPositionButton = new QPushButton(this);
    editPositionButton->setGeometry(previously_x,POSITION_HEIGHT/10,(POSITION_WIDTH-(POSITION_WIDTH*0.75))/2,
                                    POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));
    editPositionButton->setStyleSheet("border-image: url(:/img/editIcon.png);");
    editPositionButton->setCursor(Qt::PointingHandCursor);
    connect(editPositionButton,SIGNAL(clicked()),this,SLOT(editPostionButtonEvent()));

    previously_x = editPositionButton->x()+editPositionButton->width();

    addIconButton = new QPushButton(this);
    addIconButton->setText(tr("Загрузить.."));
    addIconButton->setGeometry(previously_x,POSITION_HEIGHT/10,(POSITION_WIDTH-(POSITION_WIDTH*0.6))/2,
                               POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));
    connect(addIconButton,SIGNAL(clicked()),this,SLOT(loadIconLabelEvent()));

    previously_x = addIconButton->x()+addIconButton->width();

    deletePositionButton = new QPushButton(this);
    deletePositionButton->setGeometry(previously_x,POSITION_HEIGHT/10,(POSITION_WIDTH-(POSITION_WIDTH*0.75))/2,
                                      POSITION_HEIGHT-(POSITION_HEIGHT/10)-(POSITION_HEIGHT/10));
    deletePositionButton->setStyleSheet("border-image: url(:/img/deleteIcon.png);");
    deletePositionButton->setCursor(Qt::PointingHandCursor);

    connect(deletePositionButton,SIGNAL(clicked()),this,SLOT(deleteButtonEvent()));


}

QString positions::getPositionName()
{
    return(nameLabel->text());
}

QString positions::getIconPath()
{

}

void positions::setCategoryID(QString nameCategory){
    categoryID = nameCategory;
}

QString positions::getCategoryID(){
    return(categoryID);
}

void positions::editPostionButtonEvent()
{
    qDebug()<<"EDIT POSITION";

    positionEditor* editor = new positionEditor(nullptr);
    connect(editor,SIGNAL(changePosition(QString,QString)),this,SLOT(edit(QString,QString)));
    editor->show();
}

void positions::edit(QString name, QString cost)
{
        qDebug()<<"EDITE_NAME";
        nameLabel->setText(name);
        costLabel->setText(cost);
}



void positions::deleteButtonEvent()
{
    qDebug()<<"DELETE_NAME";
}

void positions::loadIconLabelEvent()
{
    QString name_file=QFileDialog::getOpenFileName(0, "Open Dialog", "", "*.PNG *.png");
    if (name_file!=""){
        qDebug()<<name_file;
        iconLabel->setStyleSheet("border-image: url("+name_file+");");
    }
}

